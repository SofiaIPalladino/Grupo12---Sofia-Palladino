package org.persistencia;

import org.chofer.Chofer;
import org.chofer.GestionChofer;
import org.pedido.GestionPedidos;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.usuario.GestionUsuario;
import org.usuario.Usuario;
import org.vehiculo.Vehiculo;
import org.viaje.GestionViajes;
import org.viaje.IViaje;
import org.viaje.Viaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDTO implements Serializable {
    private List<Cliente> clientes;
    private List<Usuario> usuarios;
    private double recaudado;
    private GestionUsuario gestionUsuario;

    private GestionViajes gestionViajes;
    private GestionPedidos gestionPedidos;
    private GestionChofer gestionChofer;
    private int cantidadMaximaSolicitudesPorCliente;  // Máximo número de solicitudes que puede hacer un cliente.
    private int cantidadMaximaChoferesTipo;  // Máximo número de choferes permitidos por tipo.
    private int cantidadMaximaSolicitudesPorChofer;  // Máximo número de solicitudes que puede atender un chofer.
    private String informacionAccionarHilos = "";  // Información sobre la acción de hilos en la empresa.
    /**
     * Constructor necesario para la serialización.
     */
    public EmpresaDTO(){
    }

    /**
     * Constructor que inicializa todos los atributos del DTO de Empresa.
     *
     * @param clientes Lista de clientes.
     * @param usuarios Lista de usuarios.
     * @param recaudado Monto recaudado.
     */
    public EmpresaDTO(List<Cliente> clientes, List<Usuario> usuarios, double recaudado, GestionViajes gestionViajes, GestionChofer gestionChofer, GestionPedidos gestionPedidos, GestionUsuario gestionUsuario, int cantidadMaximaSolicitudesPorCliente, int cantidadMaximaChoferesTipo, int cantidadMaximaSolicitudesPorChofer, String informacionAccionarHilos) {
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.recaudado = recaudado;
        this.gestionViajes=gestionViajes;
        this.gestionUsuario=gestionUsuario;
        this.gestionChofer=gestionChofer;
        this.gestionPedidos=gestionPedidos;
        this.cantidadMaximaSolicitudesPorCliente = cantidadMaximaSolicitudesPorCliente;
        this.cantidadMaximaChoferesTipo = cantidadMaximaChoferesTipo;
        this.cantidadMaximaSolicitudesPorChofer = cantidadMaximaSolicitudesPorChofer;
        this.informacionAccionarHilos = informacionAccionarHilos;
    }

    /**
     * Getters y setters necesarios en la clase
     */


    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public double getRecaudado() {
        return recaudado;
    }

    public void setGestionChofer(GestionChofer gestionChofer) {
        this.gestionChofer = gestionChofer;
    }

    public void setGestionPedidos(GestionPedidos gestionPedidos) {
        this.gestionPedidos = gestionPedidos;
    }

    public void setGestionViajes(GestionViajes gestionViajes) {
        this.gestionViajes = gestionViajes;
    }

    public void setGestionUsuario(GestionUsuario gestionUsuario) {
        this.gestionUsuario = gestionUsuario;
    }

    public void setRecaudado(double recaudado) {
        this.recaudado = recaudado;
    }

    public GestionViajes getGestionViajes(){
        return gestionViajes;
    }

    public GestionPedidos getGestionPedidos(){
        return gestionPedidos;
    }

    public GestionUsuario getGestionUsuario(){
        return gestionUsuario;
    }

    public GestionChofer getGestionChofer(){
        return gestionChofer;
    }


    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getCantidadMaximaSolicitudesPorCliente() {
        return cantidadMaximaSolicitudesPorCliente;
    }

    public void setCantidadMaximaSolicitudesPorCliente(int cantidadMaximaSolicitudesPorCliente) {
        this.cantidadMaximaSolicitudesPorCliente = cantidadMaximaSolicitudesPorCliente;
    }

    public int getCantidadMaximaChoferesTipo() {
        return cantidadMaximaChoferesTipo;
    }

    public void setCantidadMaximaChoferesTipo(int cantidadMaximaChoferesTipo) {
        this.cantidadMaximaChoferesTipo = cantidadMaximaChoferesTipo;
    }

    public int getCantidadMaximaSolicitudesPorChofer() {
        return cantidadMaximaSolicitudesPorChofer;
    }

    public void setCantidadMaximaSolicitudesPorChofer(int cantidadMaximaSolicitudesPorChofer) {
        this.cantidadMaximaSolicitudesPorChofer = cantidadMaximaSolicitudesPorChofer;
    }

    public String getInformacionAccionarHilos() {
        return informacionAccionarHilos;
    }

    public void setInformacionAccionarHilos(String informacionAccionarHilos) {
        this.informacionAccionarHilos = informacionAccionarHilos;
    }
}
