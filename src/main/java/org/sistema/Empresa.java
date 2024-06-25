package org.sistema;

import org.chofer.Chofer;
import org.chofer.GestionChofer;
import org.controladores.ControladorChofer;
import org.excepciones.*;
import org.hilos.HiloSistema;
import org.pedido.GestionPedidos;
import org.pedido.Pedido;
import org.usuario.Cliente;
import org.usuario.GestionUsuario;
import org.usuario.Usuario;
import org.viaje.GestionViajes;
import org.viaje.IViaje;
import org.vehiculo.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Empresa extends Observable {
    private static Empresa instance = null;
    private List<Cliente> clientes;  // Lista de clientes registrados en la empresa.
    private List<Usuario> usuarios;  // Lista de usuarios registrados en la empresa (puede incluir administradores y otros tipos).
    private GestionViajes gestionViajes;  // Gestor de los viajes realizados por la empresa.
    private GestionPedidos gestionPedidos;  // Gestor de los pedidos realizados por los clientes.
    private GestionChofer gestionChofer;// Gestor de los choferes contratados por la empresa.
    private GestionUsuario gestionUsuario;
    private double recaudado;  // Monto total recaudado por la empresa.
    private int cantidadMaximaSolicitudesPorCliente;  // Máximo número de solicitudes que puede hacer un cliente.
    private int cantidadMaximaChoferesTipo;  // Máximo número de choferes permitidos por tipo.
    private int cantidadMaximaSolicitudesPorChofer;  // Máximo número de solicitudes que puede atender un chofer.
    private String informacionAccionarHilos = "";  // Información sobre la acción de hilos en la empresa.

    /**
     * Constructor privado de la clase Empresa.
     * Inicializa las listas y gestores necesarios.
     */
    private Empresa() {
        clientes = new ArrayList<>();
        usuarios = new ArrayList<>();
        gestionPedidos = new GestionPedidos();
        gestionViajes = new GestionViajes();
        gestionChofer = new GestionChofer();
        gestionUsuario= new GestionUsuario();
    }

    /**
     * Método estático para obtener la instancia única de Empresa.
     *
     * @return Instancia única de la clase Empresa.
     */
    public static Empresa getInstance() {
        if (instance == null)
            instance = new Empresa();
        return instance;
    }

    /**
     * Método para obtener la información de acciónar hilos.
     *
     * @return Información sobre la acción de hilos en la empresa.
     */
    public String getInformacionAccionarHilos() {
        return informacionAccionarHilos;
    }

    /**
     * Método para agregar información sobre la acción de hilos.
     *
     * @param informacionHilo Información a agregar sobre la acción de hilos.
     */
    public void agregarInformacionAccionarHilos(String informacionHilo) {
        this.informacionAccionarHilos += (informacionHilo) + '\n';
        this.notificarCambios(this.informacionAccionarHilos);
    }

    /**
     * Método para obtener el gestor de choferes.
     *
     * @return Instancia del gestor de choferes.
     */
    public GestionChofer getGestionChofer() {
        return gestionChofer;
    }

    /**
     * Método para obtener la cantidad máxima de solicitudes por cliente.
     *
     * @return Cantidad máxima de solicitudes que puede hacer un cliente.
     */
    public int getCantidadMaximaSolicitudesPorCliente() {
        return this.cantidadMaximaSolicitudesPorCliente;
    }

    /**
     * Método para establecer la cantidad máxima de solicitudes por cliente.
     *
     * @param cantidadMaximaSolicitudesPorCliente Cantidad máxima de solicitudes que puede hacer un cliente.
     */
    public void setCantidadMaximaSolicitudesPorCliente(int cantidadMaximaSolicitudesPorCliente) {
        this.cantidadMaximaSolicitudesPorCliente = (int) (Math.random() * cantidadMaximaSolicitudesPorCliente) + 1;
    }

    /**
     * Método para obtener la cantidad máxima de solicitudes por chofer.
     *
     * @return Cantidad máxima de solicitudes que puede atender un chofer.
     */
    public int getCantidadMaximaSolicitudesPorChofer() {
        return this.cantidadMaximaSolicitudesPorChofer;
    }

    /**
     * Método para establecer la cantidad máxima de solicitudes por chofer.
     *
     * @param cantidadMaximaSolicitudesPorChofer Cantidad máxima de solicitudes que puede atender un chofer.
     */
    public void setCantidadMaximaSolicitudesPorChofer(int cantidadMaximaSolicitudesPorChofer) {
        this.cantidadMaximaSolicitudesPorChofer = (int) ((Math.random() * cantidadMaximaSolicitudesPorChofer) + 1);
    }

    /**
     * Método para obtener la cantidad máxima de choferes por tipo.
     *
     * @return Cantidad máxima de choferes permitidos por tipo.
     */
    public int getCantidadMaximaChoferesTipo() {
        return cantidadMaximaChoferesTipo;
    }

    /**
     * Método para establecer la cantidad máxima de choferes por tipo.
     *
     * @param cantidadMaximaChoferesTipo Cantidad máxima de choferes permitidos por tipo.
     */
    public void setCantidadMaximaChoferesTipo(int cantidadMaximaChoferesTipo) {
        this.cantidadMaximaChoferesTipo = cantidadMaximaChoferesTipo;
    }

    /**
     * Método para agregar un chofer a la empresa.
     *
     * @param chofer Chofer a agregar.
     * @throws MaximoChoferesTipoException Si se excede la cantidad máxima de choferes permitidos por tipo.
     */
    public void agregaChofer(Chofer chofer) throws MaximoChoferesTipoException {
        gestionChofer.agregaChofer(chofer);
    }

    /**
     * Método para agregar un vehículo a la empresa.
     *
     * @param vehiculo Vehículo a agregar.
     */
    public void agregaVehiculo(Vehiculo vehiculo) {
        gestionViajes.agregarVehiculos(vehiculo);
    }

    /**
     * Método para agregar un cliente a la empresa (utilizado para pruebas).
     *
     * @param usuario    Nombre de usuario del cliente.
     * @param contrasenia Contraseña del cliente.
     * @param nombre     Nombre del cliente.
     * @param apellido   Apellido del cliente.
     */
    public Cliente agregaCliente(String usuario, String contrasenia, String nombre, String apellido) {
        Cliente cliente = new Cliente(usuario, contrasenia, nombre, apellido);
        this.clientes.add(cliente);
        return cliente;
    }

    /**
     * Método para obtener la lista de clientes registrados en la empresa.
     *
     * @return Lista de clientes registrados.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Método para obtener la lista de todos los usuarios registrados en la empresa.
     *
     * @return Lista de usuarios registrados.
     */
    public boolean existeUsuario(String usuario,boolean registro) throws UsuarioExistenteException {
        return gestionUsuario.existeUsuario(usuario,registro);
    }

    public Usuario validaContrasenia(String usuarioing, String contrasenia){
        return gestionUsuario.validaContrasenia(usuarioing,contrasenia);
    }

    public void agregaUsuario(String usuario, String contrasenia, String nombre, String apellido){
        gestionUsuario.agregaUsuario(usuario,contrasenia,nombre,apellido);
    }

    /**
     * Método para obtener la lista de todos los viajes realizados por la empresa.
     *
     * @return Lista de todos los viajes realizados.
     */
    public List<IViaje> getViajes() {
        return gestionViajes.getViajes();
    }


    /**
     * Método para obtener la lista de viajes finalizados por un cliente específico.
     *
     * @param cliente Cliente del cual se desean obtener los viajes finalizados.
     * @return Lista de viajes finalizados por el cliente especificado.
     */
    public List<IViaje> getViajesClienteFinalizados(Cliente cliente) {
        return gestionViajes.getViajesClienteFinalizados(cliente);
    }

    /**
     * Método para obtener el monto total recaudado por la empresa.
     *
     * @return Monto total recaudado.
     */
    public double getRecaudado() {
        return recaudado;
    }

    /**
     * Método para sumar al monto total recaudado por la empresa.
     *
     * @param monto Monto a sumar al total recaudado.
     */
    public void sumaRecaudado(double monto) {
        this.recaudado += monto;
    }

    /**
     * Método para obtener la lista de todos los usuarios registrados en la empresa.
     *
     * @return Lista de usuarios registrados.
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Método para establecer el monto total recaudado por la empresa.
     *
     * @param recaudado Monto total recaudado por la empresa.
     */
    public void setRecaudado(double recaudado) {
        this.recaudado = recaudado;
    }

    /**
     * Método para notificar cambios en un viaje específico a los observadores.
     *
     * @param viaje Viaje que ha sufrido cambios.
     */
    public void notificarCambios(IViaje viaje) {
        setChanged();
        notifyObservers(viaje);
    }

    public void notificarCambios(String stringInformacion) {
        setChanged();
        notifyObservers(stringInformacion);
    }

    /**
     * Método para obtener el gestor de viajes de la empresa.
     *
     * @return Instancia del gestor de viajes.
     */
    public GestionViajes getGestionViajes() {
        return gestionViajes;
    }

    /**
     * Método para obtener la información de acciónar hilos.
     *
     * @return Información sobre la acción de hilos en la empresa.
     */
    public String obtenerInformacionAccionarHilos() {
        return informacionAccionarHilos.toString();
    }

    // Métodos relacionados con la gestión de pedidos

    /**
     * Método para obtener el gestor de pedidos de la empresa.
     *
     * @return Instancia del gestor de pedidos.
     */
    public GestionPedidos getGestionPedidos() {
        return gestionPedidos;
    }

    public GestionUsuario getGestionUsuario(){
        return gestionUsuario;
    }
    /**
     * Método para evaluar un pedido y verificar si hay vehículo y chofer disponibles para atenderlo.
     *
     * @param p Pedido que se desea evaluar.
     * @throws NoVehiculoException Si no hay vehículos disponibles que cumplan con los requisitos del pedido.
     * @throws NoChoferException   Si no hay choferes trabajando para asignar al pedido.
     */
    public void evaluarPedido(Pedido p) throws NoVehiculoException, NoChoferException {
        gestionPedidos.evaluarPedido(p);
    }

    /**
     * Método para obtener la lista de choferes contratados por la empresa.
     *
     * @return Lista de choferes contratados.
     */
    public List<Chofer> getChoferes() {
        return gestionChofer.getChoferes();
    }

    public static void setInstance(Empresa instance) {
        Empresa.instance = instance;
    }

    public void setGestionUsuario(GestionUsuario gestionUsuario) {
        this.gestionUsuario = gestionUsuario;
    }

    public void setGestionChofer(GestionChofer gestionChofer) {
        for (Chofer chofer : gestionChofer.getChoferes()){
            new ControladorChofer(chofer);
        }
        this.gestionChofer = gestionChofer;
    }

    public void setGestionPedidos(GestionPedidos gestionPedidos) {
        this.gestionPedidos = gestionPedidos;
    }

    public void setGestionViajes(GestionViajes gestionViajes) {
        this.gestionViajes = gestionViajes;
        new HiloSistema(this.gestionViajes).start(); // Inicia el hilo de gestión de vehículos
    }

    public List<Vehiculo> getVehiculos() {
        return gestionViajes.getVehiculos();
    }

    public IViaje convertirPedidoEnViaje(Pedido p) {
        return this.gestionViajes.convertirPedidoEnViaje(p);
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setInformacionAccionarHilos(String informacionAccionarHilos) {
        this.informacionAccionarHilos = informacionAccionarHilos;
    }
}
