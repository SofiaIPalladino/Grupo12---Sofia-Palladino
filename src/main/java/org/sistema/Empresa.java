package org.sistema;


import org.chofer.Chofer;
import org.excepciones.MaximoChoferesTipoException;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.excepciones.UsuarioExistenteException;
import org.pedido.Pedido;
import org.usuario.Administrador;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.usuario.UsuarioFactory;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;


public class Empresa extends Observable {

    private static Empresa instance = null;
    private List<Cliente> clientes;
    private List<Usuario> usuarios;

    ////Cambniar esto por un hash map donde en la primera posicion este el chofer y en la segunda este si esta disponible o no
    private List<Chofer> choferes;
    private List<Chofer> choferesEnUso;
    ///*******************************************//


    ////Cambniar esto por un hash map donde en la primera posicion este el vehiculo y en la segunda este si esta disponible o no
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosEnUso;


    ///*******************************************//

    private List<IViaje> viajes;
    private List<IViaje> viajesSinChoferes;
    private List<Pedido> pedidos;
    private double recaudado = 0;
    private int cantidadMaximaSolicitudesPorCliente;
    private int cantidadMaximaChoferesTipo;
    private int cantidadMaximaSolicitudesPorChofer;
    private StringBuilder informacionAccionarHilos = new StringBuilder("");


    private Empresa() {
        viajes = new ArrayList<IViaje>();
        vehiculosEnUso = new ArrayList<Vehiculo>();
        vehiculos = new ArrayList<Vehiculo>();
        choferes = new ArrayList<Chofer>();
        choferesEnUso = new ArrayList<Chofer>();
        clientes = new ArrayList<Cliente>();
        usuarios = new ArrayList<Usuario>();
        pedidos = new ArrayList<Pedido>();
        viajesSinChoferes = new ArrayList<IViaje>();
    }

    public static synchronized Empresa getInstance() {
        if (instance == null)
            instance = new Empresa();
        return instance;
    }
    public StringBuilder getInformacionAccionarHilos() {
        return informacionAccionarHilos;
    }

    public void agregarInformacionAccionarHilos(String informacionAccionarHilos) {
        this.informacionAccionarHilos.append(informacionAccionarHilos).append("\n");
        this.notificarCambios();
    }

    public void setCantidadMaximaSolicitudesPorCliente(int cantidadMaximaSolicitudesPorCliente) {
        this.cantidadMaximaSolicitudesPorCliente = (int) (Math.random() * cantidadMaximaSolicitudesPorCliente) + 1;
    }

    public int getCantidadMaximaSolicitudesPorCliente(){
        return this.cantidadMaximaSolicitudesPorCliente;
    }

    public void setCantidadMaximaSolicitudesPorChofer(int cantidadMaximaSolicitudesPorChofer) {
        this.cantidadMaximaSolicitudesPorChofer = (int) (Math.random() * cantidadMaximaSolicitudesPorChofer) + 1;
    }

    public int getCantidadMaximaSolicitudesPorChofer(){
        return this.cantidadMaximaSolicitudesPorChofer;
    }

    public synchronized List<IViaje> getViajesSinChoferes() {
        return viajesSinChoferes;
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
        return viajes;
    }

    public List<IViaje> getViajesClienteFinalizados(Cliente cliente) {
        ArrayList<IViaje> viajesCliente = new ArrayList<>();
        Iterator<IViaje> iterator = this.getViajes().iterator();
        //System.out.println(this.getViajes());
        while (iterator.hasNext()) {
            IViaje viaje = iterator.next();
            if (viaje.getCliente().getUsuario().equals(cliente.getUsuario()) && viaje.getStatus().equals("Finalizado")) {
                viajesCliente.add(viaje);
            }
        }
        return viajesCliente;
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

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setViajes(List<IViaje> viajes) {
        this.viajes = viajes;
    }

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

    public void quitarChofer(Chofer chofer){
        this.choferes.remove(chofer);
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
        return this.pedidos;
    }

    public void agregaPedido(Pedido p) {
        this.pedidos.add(p);
    }

    public synchronized void agregoViaje(IViaje v) {
        //System.out.println(v);
        this.viajes.add(v);
        this.notificarCambios();
        //System.out.println(viajes.get(0));
    }

    private int cantidadChoferesTipo(Chofer choferTipo){
        int count = 0;
        for (Chofer chofer : choferes) {
            if (chofer.getClass().equals(choferTipo.getClass())) {
                count++;
            }
        }
        return count;
    }
    public synchronized void agregaChofer(Chofer c) throws MaximoChoferesTipoException {
        int cantidadChoferesMismoTipo = this.cantidadChoferesTipo(c);
        if (cantidadChoferesMismoTipo != Empresa.getInstance().getCantidadMaximaChoferesTipo())
            this.choferes.add(c);
        else
            throw new MaximoChoferesTipoException();
    }

    public synchronized void agregaVehiculo(Vehiculo v) {
        this.vehiculos.add(v);
    }

    public void agregaUsuario(String usuario, String contrasenia, String nombre, String apellido) {
        UsuarioFactory factoryUsuario = new UsuarioFactory();
        Usuario nuevoUsuario = new Usuario(usuario, contrasenia, nombre, apellido);
        Usuario usuarioFactory = factoryUsuario.crea(nuevoUsuario);
        this.usuarios.add(usuarioFactory);
        if (!usuarioFactory.getClass().equals(Administrador.class))
            this.clientes.add((Cliente) usuarioFactory);
    }

    public List<IViaje> getViajesChofer(Chofer chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<>();
        Iterator<IViaje> iterator = this.getViajes().iterator();
        while (iterator.hasNext()) {
            IViaje viaje = iterator.next();
            if (viaje.getChofer() != null && viaje.getChofer().getDni().equals(chofer.getDni())) {
                viajesChofer.add(viaje);
            }
        }
        return viajesChofer;
    }

    public List<Cliente> getClientesChofer(Chofer chofer) {
        List<IViaje> viajes = this.getViajesChofer(chofer);
        ArrayList<Cliente> clientes = new ArrayList<>();
        if (!viajes.isEmpty()) {
            Iterator<IViaje> iterator = this.viajes.iterator();
            while (iterator.hasNext()) {
                IViaje viaje = iterator.next();
                if (!clientes.contains(viaje.getCliente()))
                    clientes.add(viaje.getCliente());
            }
        }
        return clientes;
    }

    public void aceptaPedido(Pedido p) {
        this.pedidos.remove(p);
    }

    public boolean existeUsuario(String usuarioing, boolean registro) throws UsuarioExistenteException {
        Iterator<Usuario> iterator = this.getUsuarios().iterator();
        while (iterator.hasNext()) {
            Usuario usuarios1 = iterator.next();
            if (usuarios1.getUsuario().equals(usuarioing)) {
                if (registro)
                    throw new UsuarioExistenteException("Usuario ya existente: " + usuarioing);
                else
                    return true;
            }
        }
        return false;
    }

    public Usuario validaContrasenia(String usuarioing, String contrasenia) {
        Iterator<Usuario> iterator = this.getUsuarios().iterator();
        while (iterator.hasNext()) {
            Usuario usuarios1 = iterator.next();
            if (usuarios1.getUsuario().equals(usuarioing) && usuarios1.getContrasenia().equals(contrasenia)) {
                return usuarios1;
            }
        }
        return null;
    }

    public synchronized void finalizarViaje(IViaje viaje) {
        this.getVehiculosEnUso().remove(viaje.getVehiculo());
        this.getChoferesEnUso().remove(viaje.getChofer());
        this.notificarCambios();
    }



    public void notificarCambios() {
        setChanged();
        notifyObservers();
    }

    public int getCantidadMaximaChoferesTipo() {
        return cantidadMaximaChoferesTipo;
    }

    public void setCantidadMaximaChoferesTipo(int cantidadMaximaChoferesTipo) {
        this.cantidadMaximaChoferesTipo = cantidadMaximaChoferesTipo;
    }
}

/**
 * @param chofer
 * @param desde
 * @param hasta
 *
 * <br> precondiciones: desde tiene que ser una fecha menor o igual a hasta
 * @param chofer
 * @param desde
 * @param hasta
 *
 * <br> precondiciones: desde tiene que ser una fecha menor o igual a hasta
 */
	/*public void muestraViajes(Chofer chofer, LocalDate desde, LocalDate hasta) {
		List<IViaje> viajesChofer = getViajes(chofer);
		System.out.println("Viajes de "+chofer.getNombre());
		for(IViaje viaje: viajesChofer) {
			if (viaje.getFecha().isAfter(desde.minusDays(1)) && viaje.getFecha().isBefore(hasta.plusDays(1))) {				
				System.out.println(viaje);
			}
		}
	}
*/
/**
 *
 * @param chofer
 * @param desde
 * @param hasta
 *
 * <br> precondiciones: desde tiene que ser una fecha menor o igual a hasta
 */
	/*public void muestraViajes(Cliente cliente, LocalDate desde, LocalDate hasta) {
		List<IViaje> viajesChofer = getViajes(cliente);
		System.out.println("Viajes de "+cliente.getNombre());
		for(IViaje viaje: viajesChofer) {
			if (viaje.getFecha().isAfter(desde.minusDays(1)) && viaje.getFecha().isBefore(hasta.plusDays(1))) {				
				System.out.println(viaje);
			}
		}
	}*/
	

	
	
	

	
	