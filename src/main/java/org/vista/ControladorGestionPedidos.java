package org.vista;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.excepciones.ViajeNoEncontradoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;
import org.viaje.ViajeFactory;

import java.util.*;

public class ControladorGestionPedidos {
    private Empresa empresa;

    public ControladorGestionPedidos() {
        this.empresa = Empresa.getInstance();
        this.crearMonitorPedidos();
    }

    public void convertirPedidosEnViajes() throws NoVehiculoException, NoChoferException {
        List<Pedido> pedidos = this.getPedidos();
        Pedido pedido = null;
        int i = 0;
        while (i < pedidos.size()) {
            pedido = pedidos.get(i);
            ViajeFactory viajeFactory = new ViajeFactory();
            IViaje viaje = viajeFactory.getViaje(pedido);
            empresa.agregoViaje(viaje);
            new VentanaViajeActual(viaje).setVisible(true);
            empresa.aceptaPedido(pedido);
            empresa.agregarInformacionAccionarHilos(viaje.getCliente().getUsuario()+ " ha solicitado un viaje.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.asignarVehiculoHilo(viaje);
            i++;
        }
    }


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

    public Map<Integer, Vehiculo> buscarVehiculosAptos(Pedido pedido) {
        Map<Integer, Vehiculo> vehiculosCumplen = new TreeMap<>(Comparator.reverseOrder());
        int i = 0;
        Vehiculo vehiculo = null;
        while (i < empresa.getVehiculos().size()) {
            vehiculo = empresa.getVehiculos().get(i);
            if (vehiculo.verificaPasajeros(pedido.getCantPersonas()) && vehiculo.verificaBaul(pedido.usoBaul()) && vehiculo.verificaMascota(pedido.getMascota())) {
                vehiculosCumplen.put(vehiculo.getPrioridad(pedido), vehiculo);
            }
            i++;
        }
        return vehiculosCumplen;
    }

    public void crearMonitorPedidos() {
        Runnable hiloCrearViaje = new MonitorPedidos(this);
        Thread hilo = new Thread(hiloCrearViaje);
        hilo.start();
    }

    public List<Pedido> getPedidos() {
        return empresa.getPedidos();
    }

    public void asignarVehiculoHilo(IViaje viaje) {
        Runnable vehiculoHilo = new CrearVehiculoHilo(this, viaje);
        Thread hilo = new Thread(vehiculoHilo);
        hilo.start();
    }

    public List<IViaje> getViajes() {
        return empresa.getViajes();
    }
}
