package org.controladores;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;
import org.viaje.ViajeFactory;
import org.vehiculo.CrearVehiculoHilo;
import org.monitor.MonitorPedidos;
import org.vista.VentanaViajeActual;

import java.util.*;

/**
 * Controlador para la gestión de pedidos en la empresa.
 * Convierte los pedidos en viajes y asigna vehículos a dichos viajes.
 */
public class ControladorGestionPedidos {
    private Empresa empresa;

    public ControladorGestionPedidos() {
        this.empresa = Empresa.getInstance();
        this.crearMonitorPedidos();
    }


    /**
     * Asigna un vehículo a un viaje.
     *
     * @param viaje El viaje al que se le asignará un vehículo.
     * @throws NoVehiculoException Si no hay vehículos disponibles.
     */
    public synchronized void asignarVehiculo(IViaje viaje) throws NoVehiculoException {
        Vehiculo mejorVehiculo = null;
        empresa.agregarInformacionAccionarHilos("El viaje de " + viaje.getCliente().getUsuario()+ " está buscando vehículo.");
        while (mejorVehiculo == null) {
            Map<Integer, Vehiculo> vehiculosAptos = buscarVehiculosAptos(viaje.getPedido());
            if (vehiculosAptos == null) {
                throw new NoVehiculoException("No hay vehiculos aptos trabajando en este momento");
            }
            for (Map.Entry<Integer, Vehiculo> entry : vehiculosAptos.entrySet()) {
                Vehiculo v = entry.getValue();

                if (!empresa.getVehiculosEnUso().contains(v)) {
                    mejorVehiculo = v;
                    break;
                }
            }
            if (mejorVehiculo == null) {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new NoVehiculoException("La espera fue interrumpida");
                }
            }
        }
        empresa.setVehiculoConViaje(mejorVehiculo);
        viaje.setVehiculo(mejorVehiculo);
        viaje.setStatus("Con Vehiculo");
        empresa.agregarInformacionAccionarHilos("El viaje de " + viaje.getCliente().getUsuario()+ " encontró vehículo");
        notifyAll();
        empresa.setViajesSinChoferes(viaje);
        empresa.notificarCambios();
    }



    /**
     * Metodo que se encarga de crear un monitor para observar los pedidos y generar viajes.
     */
    public void crearMonitorPedidos() {
        Runnable hiloCrearViaje = new MonitorPedidos(this);
        Thread hilo = new Thread(hiloCrearViaje);
        hilo.start();
    }

    public List<Pedido> getPedidos() {
        return empresa.getPedidos();
    }

    /**
     * Metodo encargado de la creacion de un hilo para asignar un vehículo a un viaje.
     *
     * @param viaje El viaje al que se le asignará un vehículo.
     */
    public void asignarVehiculoHilo(IViaje viaje) {
        Runnable vehiculoHilo = new CrearVehiculoHilo(this, viaje);
        Thread hilo = new Thread(vehiculoHilo);
        hilo.start();
    }

    public List<IViaje> getViajes() {
        return empresa.getViajes();
    }
}
