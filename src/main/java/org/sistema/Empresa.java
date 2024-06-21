package org.sistema;


import org.chofer.Chofer;
import org.controladores.ControladorGestionPedidos;
import org.controladores.ControladorVentanaChofer;
import org.controladores.ControladorViajeActual;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.excepciones.UsuarioExistenteException;
import org.excepciones.ViajeNoEncontradoException;
import org.pedido.GestionPedidos;
import org.pedido.Pedido;
import org.usuario.Administrador;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.usuario.UsuarioFactory;
import org.vehiculo.Vehiculo;
import org.viaje.GestionViajes;
import org.viaje.IViaje;
import org.viaje.Viaje;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;


public class Empresa extends Observable {

    private static Empresa instance = null;
    private List<Cliente> clientes;
    private List<Usuario> usuarios;

    private List<Chofer> choferes;
    private List<Chofer> choferesEnUso;

    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosEnUso;

    private List<IViaje> viajesSinChoferes;
    private GestionViajes gestionViajes;
    private GestionPedidos gestionPedidos;

    private double recaudado = 0;



    private String usuariolog;


    private Empresa() {
        //viajes = new ArrayList<IViaje>();
        vehiculosEnUso = new ArrayList<Vehiculo>();
        vehiculos = new ArrayList<Vehiculo>();
        choferes = new ArrayList<Chofer>();
        choferesEnUso = new ArrayList<Chofer>();
        clientes = new ArrayList<Cliente>();
        usuarios = new ArrayList<Usuario>();
        //pedidos = new ArrayList<Pedido>();
        viajesSinChoferes = new ArrayList<IViaje>();
        gestionViajes = new GestionViajes(this);
        gestionPedidos = new GestionPedidos(this);
        new ControladorGestionPedidos(this);
    }

    public static synchronized Empresa getInstance() {
        if (instance == null)
            instance = new Empresa();
        return instance;
    }

    public synchronized List<IViaje> getViajesSinChoferes() {
        return viajesSinChoferes;
    }

    public List<IViaje> getViajesChofer(Chofer chofer){
        return gestionViajes.getViajesChofer(chofer);
    }

    public void setViajesSinChoferes(IViaje viajeSinChofer) {
        this.viajesSinChoferes.add(viajeSinChofer);
    }

    public synchronized List<Cliente> getClientes() {
        return clientes;
    }

    public synchronized List<Chofer> getChoferes() {
        return choferes;
    }

    public synchronized List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setUsuariolog(String usuariolog) {
        this.usuariolog = usuariolog;
    }

    public List<Chofer> getChoferesEnUso() {
        return choferesEnUso;
    }

    public List<Usuario> getUsuario() {
        return usuarios;
    }


    public synchronized List<Vehiculo> getVehiculosEnUso() {
        return vehiculosEnUso;
    }

    public synchronized List<IViaje> getViajes() {
        return gestionViajes.getViajes();
    }

    public synchronized List<IViaje> getViajesClienteFinalizados(Cliente cliente) {
        return gestionViajes.getViajesClienteFinalizados(cliente);
    }

    public double getRecaudado() {
        return recaudado;
    }

    public void sumaRecaudado(double monto) {
        this.recaudado += monto;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setRecaudado(double recaudado) {
        this.recaudado = recaudado;
    }

    //public void setPedidos(List<Pedido> pedidos) {this.pedidos = pedidos;}

    //public void setViajes(List<IViaje> viajes) {this.viajes = viajes;}

    public void setVehiculosEnUso(List<Vehiculo> vehiculosEnUso) {
        this.vehiculosEnUso = vehiculosEnUso;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setChoferesEnUso(List<Chofer> choferesEnUso) {
        this.choferesEnUso = choferesEnUso;
    }

    public void setChoferes(List<Chofer> choferes) {
        this.choferes = choferes;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public static void setInstance(Empresa instance) {
        Empresa.instance = instance;
    }

    public void setChoferConViaje(Chofer c) throws NoChoferException {
        this.choferesEnUso.add(c);
    }

    public void setChoferDisponible(Chofer c) throws NoChoferException {
        if (!choferesEnUso.remove(c))
            throw new NoChoferException("El chofer seleccionado no esta en uso");
    }


    public synchronized void setVehiculoConViaje(Vehiculo v) throws NoVehiculoException {
        this.vehiculosEnUso.add(v);
    }

    public void setVehiculoDisponible(Vehiculo v) throws NoVehiculoException {
        if (!vehiculosEnUso.remove(v))
            throw new NoVehiculoException("El vehiculo seleccionado no esta en uso");
        this.vehiculos.add(v);
    }

    public synchronized List<Pedido> getPedidos() {
        return gestionPedidos.getPedidos();
    }

    public synchronized void agregaChofer(Chofer c) {
        this.choferes.add(c);
        new ControladorVentanaChofer(this,c);
    }

    public synchronized void agregaVehiculo(Vehiculo v) {
        this.vehiculos.add(v);
    }

    public synchronized void pagarViaje(IViaje viaje) throws ViajeNoEncontradoException {
        gestionViajes.pagarViaje(viaje);
    }

    public void notificarCambios(IViaje viaje) {
        setChanged();
        notifyObservers(viaje);
    }

    public GestionViajes getGestionViajes() {
        return gestionViajes;
    }


    ///todo: GESTIÓN DE PEDIDOS

    public GestionPedidos getGestionPedidos() {
        return gestionPedidos;
    }


    public void evaluarPedido(Pedido p) throws NoVehiculoException, NoChoferException {
        gestionPedidos.evaluarPedido(p);
    }

}

	
	
	

	
	