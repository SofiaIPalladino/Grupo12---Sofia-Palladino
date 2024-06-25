package org.viaje;

import org.chofer.Chofer;
import org.excepciones.ViajeNoEncontradoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.hilos.HiloSistema;
import org.vehiculo.Vehiculo;

import java.io.Serializable;
import java.util.*;

/**
 * La clase GestionViajes gestiona los viajes pendientes, viajes con vehículo asignado, y los viajes en general.
 * También administra los vehículos disponibles y provee métodos para convertir pedidos en viajes, agregar viajes,
 * gestionar pagos, finalizar viajes, y obtener listas de viajes filtrados por cliente y chofer.
 */
public class GestionViajes extends Observable implements Serializable {
    protected List<IViaje> viajesPendientes;
    protected List<IViaje> viajesConVehiculo;
    protected List<IViaje> viajes;
    protected List<Vehiculo> vehiculos;

    /**
     * Constructor de la clase GestionViajes. Inicializa las listas de viajes y vehículos, y comienza un hilo de gestión
     * de vehículos utilizando la clase GestionVehiculo.
     */
    public GestionViajes() {
        this.vehiculos = new ArrayList<>();
        this.viajesPendientes = new ArrayList<>();
        this.viajesConVehiculo = new ArrayList<>();
        this.viajes = new ArrayList<>();
        new HiloSistema(this).start(); // Inicia el hilo de gestión de vehículos
    }


    /**
     * Convierte un pedido en un objeto IViaje utilizando una fábrica de viajes (ViajeFactory).
     *
     * @param pedido Pedido que se desea convertir en un viaje.
     * @return Objeto IViaje generado a partir del pedido.
     */
    public IViaje convertirPedidoEnViaje(Pedido pedido) {
        ViajeFactory viajeFactory = new ViajeFactory();
        return viajeFactory.getViaje(pedido);
    }

    /**
     * Agrega un viaje a la lista de viajes pendientes y notifica a los hilos que están esperando.
     *
     * @param viaje Viaje que se desea agregar.
     */
    public void agregarViaje(IViaje viaje) {
        System.out.println("Viaje creado con éxito: " + viaje);
        synchronized (viajesPendientes) {
            viajesPendientes.add(viaje);
            viajesPendientes.notify(); // Notifica a los hilos que están esperando
        }
        viajes.add(viaje); // Agrega el viaje a la lista general de viajes
    }

    /**
     * Obtiene un viaje con vehículo asignado para un chofer específico, esperando si no hay viajes disponibles.
     *
     * @param chofer Chofer para el cual se desea obtener un viaje con vehículo asignado.
     * @return Viaje con vehículo asignado.
     * @throws InterruptedException Si ocurre una interrupción mientras el hilo está esperando.
     */
    public IViaje obtenerViajeConVehiculo(Chofer chofer) throws InterruptedException {
        synchronized (viajesConVehiculo) {
            while (viajesConVehiculo.isEmpty()) {
                System.out.println("Chofer " + chofer.getNombre() + " entró al wait de obtenerViajeConVehiculo");
                viajesConVehiculo.wait(); // Espera si no hay viajes con vehículo asignado disponibles
            }
            System.out.println("Chofer " + chofer.getNombre() + " se despertó del wait de obtenerViajeConVehiculo");
            return viajesConVehiculo.remove(0); // Remueve y retorna el primer viaje de la lista
        }
    }

    /**
     * Agrega un viaje con vehículo asignado a la lista correspondiente y notifica a los observadores.
     *
     * @param viaje Viaje con vehículo asignado que se desea agregar.
     */
    public void agregarViajeConVehiculo(IViaje viaje) {
        synchronized (viajesConVehiculo) {
            viajesConVehiculo.add(viaje);
            viajesConVehiculo.notifyAll(); // Notifica a los hilos que están esperando
        }
        System.out.println("Termino agregaViajeConVehiculo");
        notificarCambios(viaje); // Notifica a los observadores que ha habido cambios en el viaje
    }

    /**
     * Realiza el pago de un viaje y notifica a los observadores.
     *
     * @param viaje Viaje para el cual se desea realizar el pago.
     * @throws ViajeNoEncontradoException Si el viaje no se encuentra en la lista de viajes.
     */
    public void pagarViaje(IViaje viaje) throws ViajeNoEncontradoException {
        viaje.getCliente().pagoViaje(viaje); // Realiza el pago del viaje a través del cliente asociado
        notificarCambios(viaje); // Notifica a los observadores que ha habido cambios en el viaje
        synchronized (viaje) {
            viaje.notify(); // Notifica a los hilos que están esperando
        }
    }

    /**
     * Finaliza un viaje liberando el vehículo asociado y notificando a los observadores.
     *
     * @param viaje Viaje que se desea finalizar.
     */
    public void finalizarViaje(IViaje viaje) {
        synchronized (viaje) {
            synchronized (viaje.getVehiculo()) {
                viaje.getVehiculo().setEstado("Libre"); // Establece el estado del vehículo como libre
                viaje.getVehiculo().notifyAll(); // Notifica a todos los hilos que están esperando al vehículo
            }
            viaje.notifyAll(); // Notifica a todos los hilos que están esperando al viaje
        }
        synchronized (this) {
            notifyAll(); // Notifica a todos los hilos que están esperando en la instancia de GestionViajes
        }
        this.notificarCambios(viaje); // Notifica a los observadores que ha habido cambios en el viaje
    }

    /**
     * Obtiene la lista de todos los viajes gestionados.
     *
     * @return Lista de todos los viajes.
     */
    public List<IViaje> getViajes() {
        return viajes;
    }

    /**
     * Obtiene la lista de viajes finalizados de un cliente específico.
     *
     * @param cliente Cliente para el cual se desea obtener los viajes finalizados.
     * @return Lista de viajes finalizados del cliente.
     */
    public List<IViaje> getViajesClienteFinalizados(Cliente cliente) {
        List<IViaje> viajesFinalizados = new ArrayList<>();
        for (IViaje viaje : viajes) {
            if ((viaje.getCliente().compareTo(cliente) == 0) && viaje.getStatus().equals("Finalizado")) {
                viajesFinalizados.add(viaje);
            }
        }
        return viajesFinalizados;
    }

    /**
     * Obtiene la lista de viajes finalizados por un chofer específico.
     *
     * @param chofer Chofer para el cual se desea obtener los viajes finalizados.
     * @return Lista de viajes finalizados por el chofer.
     */
    public List<IViaje> getViajesChofer(Chofer chofer) {
        List<IViaje> viajesChofer = new ArrayList<>();
        for (IViaje viaje : viajes) {
            if (viaje.getChofer().equals(chofer) && viaje.getStatus().equals("Finalizado")) {
                viajesChofer.add(viaje);
            }
        }
        return viajesChofer;
    }

    /**
     * Obtiene la lista de vehículos disponibles.
     *
     * @return Lista de vehículos disponibles.
     */
    public synchronized List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     * Agrega un vehículo a la lista de vehículos disponibles.
     *
     * @param vehiculo Vehículo que se desea agregar.
     */
    public void agregarVehiculos(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    /**
     * Obtiene la lista de viajes pendientes.
     *
     * @return Lista de viajes pendientes.
     */
    public List<IViaje> getViajesPendientes() {
        return viajesPendientes;
    }

    /**
     * Notifica a los observadores sobre los cambios en un viaje.
     *
     * @param viaje Viaje que ha experimentado cambios.
     */
    public void notificarCambios(IViaje viaje) {
        Empresa.getInstance().notificarCambios(viaje); // Utiliza un singleton Empresa para notificar los cambios
    }

    public List<IViaje> getViajesConVehiculo() {
        return this.viajesConVehiculo;
    }

    public void setViajesPendientes(List<IViaje> viajesPendientes) {
        this.viajesPendientes = viajesPendientes;
    }

    public void setViajesConVehiculo(List<IViaje> viajesConVehiculo) {
        this.viajesConVehiculo = viajesConVehiculo;
    }

    public void setViajes(List<IViaje> viajes) {
        this.viajes = viajes;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
