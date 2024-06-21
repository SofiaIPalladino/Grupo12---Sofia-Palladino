package org.vehiculo;

import org.viaje.GestionViajes;
import org.viaje.IViaje;
import org.pedido.Pedido;

import java.util.*;
import org.sistema.Empresa;


public class GestionVehiculo extends Thread {
    private final GestionViajes gestionViajes;

    public GestionVehiculo(GestionViajes gestionViajes) {
        this.gestionViajes = gestionViajes;
    }

    @Override
    public void run() {
        while (true) {
            IViaje viaje;
            synchronized (gestionViajes.getViajesPendientes()) {
                while (gestionViajes.getViajesPendientes().isEmpty()) {
                    try {
                        gestionViajes.getViajesPendientes().wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                viaje = gestionViajes.getViajesPendientes().remove(0);
            }
            try {
                asignarVehiculo(viaje);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private void asignarVehiculo(IViaje viaje) throws InterruptedException {
        Vehiculo vehiculo = esperarVehiculoDisponible(viaje);
        synchronized (viaje) {
            viaje.asignarVehiculo(vehiculo);
            viaje.notifyAll();
        }
        Thread.sleep(2000);
        gestionViajes.agregarViajeConVehiculo(viaje);
    }

    private Vehiculo esperarVehiculoDisponible(IViaje viaje) throws InterruptedException {
        while (true) {
            Map<Integer, Vehiculo> vehiculosCumplen = buscarVehiculosAptos(viaje.getPedido());
            for (Vehiculo vehiculo : vehiculosCumplen.values()) {
                synchronized (vehiculo) {
                    if (vehiculo.getEstado().equals("Libre")) {
                        vehiculo.setEstado("Ocupado");
                        return vehiculo;
                    }
                }
            }
            synchronized (gestionViajes) {
                gestionViajes.wait();
            }
        }
    }

    private Map<Integer, Vehiculo> buscarVehiculosAptos(Pedido pedido) {
        Map<Integer, Vehiculo> vehiculosCumplen = new TreeMap<>(Comparator.reverseOrder());
        List<Vehiculo> vehiculos = Empresa.getInstance().getVehiculos();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.verificaPasajeros(pedido.getCantPersonas()) &&
                    vehiculo.verificaBaul(pedido.usoBaul()) &&
                    vehiculo.verificaMascota(pedido.getMascota())) {
                vehiculosCumplen.put(vehiculo.getPrioridad(pedido), vehiculo);
            }
        }
        return vehiculosCumplen;
    }
}
