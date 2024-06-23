package org.viaje;

import org.chofer.Chofer;
import org.excepciones.ViajeNoEncontradoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.vehiculo.GestionVehiculo;
import org.vehiculo.Vehiculo;

import java.util.*;

public class GestionViajes extends Observable {
    private final List<IViaje> viajesPendientes;
    private final List<IViaje> viajesConVehiculo;
    private final List<IViaje> viajes;
    private final List<Vehiculo> vehiculos;


    public GestionViajes() {
        this.vehiculos = new ArrayList<>();
        this.viajesPendientes = new ArrayList<>();
        this.viajesConVehiculo = new ArrayList<>();
        this.viajes = new ArrayList<>();
        new GestionVehiculo(this).start();
    }

    public IViaje convertirPedidoEnViaje(Pedido pedido) {
        ViajeFactory viajeFactory = new ViajeFactory();
        return viajeFactory.getViaje(pedido);
    }

    public void agregarViaje(IViaje viaje) {
        System.out.println("Viaje creado con éxito: " + viaje);
        synchronized (viajesPendientes) {
            viajesPendientes.add(viaje);
            viajesPendientes.notify();
        }
        viajes.add(viaje);
    }

    public IViaje obtenerViajeConVehiculo(Chofer chofer) throws InterruptedException {
        synchronized (viajesConVehiculo) {
            while (viajesConVehiculo.isEmpty()) {
                System.out.println("Chofer " + chofer.getNombre() + " entró al wait de obtenerViajeConVehiculo");
                viajesConVehiculo.wait();
            }
            System.out.println("Chofer " + chofer.getNombre() + " se despertó del wait de obtenerViajeConVehiculo");
            return viajesConVehiculo.remove(0);
        }
    }

    public void agregarViajeConVehiculo(IViaje viaje) {
        synchronized (viajesConVehiculo) {
            viajesConVehiculo.add(viaje);
            viajesConVehiculo.notify();
        }
        notificarCambios(viaje);
    }

    public void pagarViaje(IViaje viaje) throws ViajeNoEncontradoException {
        System.out.println("woiwehf");
        viaje.getCliente().pagoViaje(viaje);
        System.out.println("salió de pagar viaje Viaje");
        notificarCambios(viaje);
        synchronized (viaje) {
            viaje.notify();
        }
    }

    public void finalizarViaje(IViaje viaje) {
        synchronized (viaje) {
            synchronized (viaje.getVehiculo()) {
                viaje.getVehiculo().setEstado("Libre");
                viaje.getVehiculo().notifyAll();
            }
            viaje.notifyAll();
        }
        synchronized (this) {
            notifyAll();
        }
        this.notificarCambios(viaje);
    }


    public List<IViaje> getViajes() {
        return viajes;
    }

    public List<IViaje> getViajesClienteFinalizados(Cliente cliente) {
        List<IViaje> viajesFinalizados = new ArrayList<>();
        for (IViaje viaje : viajes) {
            if (viaje.getCliente().equals(cliente) && viaje.getStatus().equals("Finalizado")) {
                viajesFinalizados.add(viaje);
            }
        }
        return viajesFinalizados;
    }

    public List<IViaje> getViajesChofer(Chofer chofer) {
        List<IViaje> viajesChofer = new ArrayList<IViaje>();
        for (IViaje viaje : viajes) {
            if (viaje.getChofer().equals(chofer) && viaje.getStatus().equals("Finalizado")) {
                viajesChofer.add(viaje);
            }
        }
        return viajesChofer;
    }

    public synchronized List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void agregarVehiculos(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    public List<IViaje> getViajesPendientes() {
        return viajesPendientes;
    }

    public void notificarCambios(IViaje viaje) {
        Empresa.getInstance().notificarCambios(viaje);
    }

}

