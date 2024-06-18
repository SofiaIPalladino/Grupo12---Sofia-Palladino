package org.pedido;

import org.chofer.Chofer;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;
import org.viaje.ViajeFactory;

import java.util.*;

public class GestionViajes {
    private Empresa empresa;
    private ViajeFactory viajeFactory = new ViajeFactory();

    public GestionViajes() {
        this.empresa = Empresa.getInstance();
    }


    /**
     * precond: corroborar que el pedido cumpla con las condiciones del viaje
     *
     * @param pedido
     */

    public IViaje convertirPedidoEnViaje(Pedido pedido) {
        IViaje viaje = viajeFactory.getViaje(pedido);
        this.empresa.getViajesSinChoferes().add(viaje);
        return viaje;
    }


    /**
     * Metodo que se encarga de buscar los vehículos que son aptos para el pedido.
     *
     * @param pedido El pedido para el que se buscan vehículos.
     * @return Un mapa de vehículos aptos ordenados por prioridad.
     */

    private Vehiculo buscarVehiculoApto(Pedido pedido) {
        int i = 0;
        int prioridad = -1;
        Vehiculo vehiculo = null;
        for (Vehiculo v : empresa.getVehiculos()) {
            if (v.verificaPasajeros(pedido.getCantPersonas()) && v.verificaBaul(pedido.usoBaul()) && v.verificaMascota(pedido.getMascota())) {
                if (v.getPrioridad(pedido) > prioridad) {
                    prioridad = v.getPrioridad(pedido);
                    vehiculo = v;
                }
            }
        }
        return vehiculo;
    }

    public void finalizarViaje(IViaje viaje) {
        this.empresa.setVehiculoDisponible(viaje.getVehiculo());
        this.empresa.getChoferesEnUso().remove(viaje.getChofer());
    }


    public void asignarVehiculo(IViaje viaje) throws NoVehiculoException {
        Vehiculo vehiculo = buscarVehiculoApto(viaje.getPedido());
        if (vehiculo == null) {
            throw new NoVehiculoException("No hay vehiculos aptos trabajando en este momento");
        }
    }

    /**
     * Metodo que evalua un pedido para verificar si hay vehículos y choferes disponibles.
     * Si están disponibles, agrega el pedido a la empresa.
     *
     * @param p El pedido a evaluar.
     * @throws NoVehiculoException Si no hay vehículos disponibles.
     * @throws NoChoferException   Si no hay choferes disponibles.
     */
    public void evaluaPedido(Pedido p) throws NoVehiculoException, NoChoferException {
        try {
            buscarVehiculo(p.getCantPersonas(), p.usoBaul(), p.getMascota());
            buscarChofer(empresa.getChoferes());
        } catch (NoVehiculoException | NoChoferException e) {
            throw (e);
        }

    }

    /**
     * Metodo que se encarga de buscar un vehículo disponible que cumpla con los requisitos del pedido.
     *
     * @param cantPasajeros La cantidad de pasajeros requeridos.
     * @param baul          Si se requiere baúl.
     * @param mascota       Si se permite mascota.
     * @return true si hay un vehículo disponible que cumpla con los requisitos.
     * @throws NoVehiculoException Si no hay vehículos disponibles que cumplan con los requisitos.
     */
    private boolean buscarVehiculo(int cantPasajeros, boolean baul, boolean mascota) throws NoVehiculoException {
        List<Vehiculo> v = empresa.getVehiculos();
        ArrayList<Vehiculo> vehiculosCumplen = new ArrayList<Vehiculo>();
        int i = 0;
        Vehiculo p = null;
        while (i < v.size()) {
            p = v.get(i);
            if (p.verificaPasajeros(cantPasajeros) && p.verificaBaul(baul) && p.verificaMascota(mascota)) {
                return true;
            }
            i++;
        }
        throw new NoVehiculoException();
    }

    /**
     * Metodo que se encarga de devolver si existe un chofer disponible.
     *
     * @param choferes La lista de choferes a evaluar.
     * @return true si hay un chofer disponible.
     * @throws NoChoferException Si no hay choferes disponibles.
     */
    private boolean buscarChofer(List<Chofer> choferes) throws NoChoferException {
        for (Chofer c : choferes) {
            return true;
        }
        throw new NoChoferException("NO existe un chofer disponible");
    }

    public void pagoViaje(IViaje viaje) {
        viaje.setStatus("Pagado");
        Empresa e = Empresa.getInstance();
        e.sumaRecaudado(viaje.getCosto());
        System.out.println("Viaje pagado con exito");
    }


}
