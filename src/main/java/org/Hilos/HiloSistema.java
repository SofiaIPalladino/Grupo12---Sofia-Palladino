package org.hilos;

import org.sistema.Empresa;
import org.vehiculo.Vehiculo;
import org.viaje.GestionViajes;
import org.viaje.IViaje;
import org.pedido.Pedido;

import java.io.Serializable;
import java.util.*;

/**
 * La clase HiloSistema representa un hilo encargado de asignar vehículos a los viajes pendientes.
 * Se encarga de gestionar la asignación de vehículos aptos para cada viaje y actualizar el estado de los vehículos.
 */
public class HiloSistema extends Thread{
    private GestionViajes gestionViajes;

    /**
     * Constructor que inicializa el hilo del sistema con una instancia de GestionViajes.
     *
     * @param gestionViajes Instancia de GestionViajes que gestiona los viajes y vehículos.
     */
    public HiloSistema(GestionViajes gestionViajes) {
        this.gestionViajes = gestionViajes;
    }

    /**
     * Método principal que se ejecuta cuando el hilo GestionVehiculo inicia.
     * Se encarga de asignar vehículos a los viajes pendientes de manera continua.
     */
    @Override
    public void run() {
        while (true) {
            IViaje viaje;
            synchronized (gestionViajes.getViajesPendientes()) {
                // Espera activa mientras no haya viajes pendientes
                while (gestionViajes.getViajesPendientes().isEmpty()) {
                    try {
                        gestionViajes.getViajesPendientes().wait(); // Espera hasta que haya un viaje pendiente
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                viaje = gestionViajes.getViajesPendientes().remove(0); // Obtiene y remueve el primer viaje pendiente
                Empresa.getInstance().agregarInformacionAccionarHilos("El sistema ha asignado un viaje");
            }
            try {
                asignarVehiculo(viaje); // Intenta asignar un vehículo al viaje
                Empresa.getInstance().agregarInformacionAccionarHilos("El sistema ha asignado un vehiculo al viaje");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    /**
     * Método para asignar un vehículo a un viaje específico.
     *
     * @param viaje Viaje al cual se le asignará un vehículo.
     * @throws InterruptedException Si se interrumpe la asignación del vehículo.
     */
    private void asignarVehiculo(IViaje viaje) throws InterruptedException {
        Vehiculo vehiculo = esperarVehiculoDisponible(viaje); // Espera a que haya un vehículo disponible
        synchronized (viaje) {
            viaje.asignarVehiculo(vehiculo); // Asigna el vehículo al viaje
            viaje.notifyAll(); // Notifica a todos los hilos que están esperando por el viaje
        }
        Thread.sleep(2000);
        System.out.println("Asigna vehiculo");// Simula un tiempo de asignación de vehículo
        gestionViajes.agregarViajeConVehiculo(viaje); // Agrega el viaje con vehículo asignado a la gestión de viajes
    }

    /**
     * Método para esperar a que haya un vehículo disponible para asignar al viaje.
     *
     * @param viaje Viaje al cual se le asignará un vehículo.
     * @return Vehículo disponible para asignar al viaje.
     * @throws InterruptedException Si se interrumpe la espera por un vehículo disponible.
     */
    private Vehiculo esperarVehiculoDisponible(IViaje viaje) throws InterruptedException {
        while (true) {
            Map<Integer, Vehiculo> vehiculosCumplen = buscarVehiculosAptos(viaje.getPedido()); // Busca vehículos aptos
            for (Vehiculo vehiculo : vehiculosCumplen.values()) {
                synchronized (vehiculo) {
                    if (vehiculo.getEstado().equals("Libre")) {
                        vehiculo.setEstado("Ocupado"); // Marca el vehículo como ocupado
                        return vehiculo; // Retorna el vehículo disponible
                    }
                }
            }
            synchronized (gestionViajes) {
                gestionViajes.wait(); // Espera hasta que haya algún cambio en la gestión de viajes
            }
        }
    }

    /**
     * Método para buscar vehículos aptos para un pedido específico.
     *
     * @param pedido Pedido para el cual se buscan vehículos aptos.
     * @return Mapa de vehículos aptos ordenados por prioridad.
     */
    private Map<Integer, Vehiculo> buscarVehiculosAptos(Pedido pedido) {
        Map<Integer, Vehiculo> vehiculosCumplen = new TreeMap<>(Comparator.reverseOrder());
        List<Vehiculo> vehiculos = this.gestionViajes.getVehiculos(); // Obtiene la lista de vehículos disponibles
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.verificaPasajeros(pedido.getCantPersonas()) &&
                    vehiculo.verificaBaul(pedido.usoBaul()) &&
                    vehiculo.verificaMascota(pedido.getMascota())) {
                vehiculosCumplen.put(vehiculo.getPrioridad(pedido), vehiculo); // Agrega vehículos aptos al mapa
            }
        }
        return vehiculosCumplen;
    }

}
