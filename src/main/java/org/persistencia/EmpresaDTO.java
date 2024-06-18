package org.persistencia;

import org.chofer.Chofer;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;
import org.viaje.Viaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDTO implements Serializable {
    private List<Cliente> clientes;
    private List<Usuario> usuarios;

    private List<Chofer> choferes;
    private List<Chofer> choferesEnUso;
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosEnUso;

    private List<IViaje> viajes;
    private List<Pedido> pedidos;
    private double recaudado;

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
     * @param choferes Lista de choferes.
     * @param choferesEnUso Lista de choferes en uso.
     * @param vehiculos Lista de vehículos.
     * @param vehiculosEnUso Lista de vehículos en uso.
     * @param viajes Lista de viajes.
     * @param pedidos Lista de pedidos.
     * @param recaudado Monto recaudado.
     */
    public EmpresaDTO(List<Cliente> clientes,List<Usuario>usuarios,List<Chofer>choferes,List<Chofer>choferesEnUso,List<Vehiculo>vehiculos,List<Vehiculo>vehiculosEnUso,List<IViaje>viajes,List<Pedido> pedidos,double recaudado) {
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.choferes =choferes;
        this.choferesEnUso = choferesEnUso;
        this.vehiculos = vehiculos;
        this.vehiculosEnUso = vehiculosEnUso;
        this.viajes =viajes;
        this.pedidos=pedidos;
        this.recaudado = recaudado;
    }


    /**
     * Getters y setters necesarios en la clase
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setViajes(List<IViaje> viajes) {
        this.viajes = viajes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Chofer> getChoferes() {
        return choferes;
    }

    public void setChoferes(List<Chofer> choferes) {
        this.choferes = choferes;
    }

    public List<Chofer> getChoferesEnUso() {
        return choferesEnUso;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Vehiculo> getVehiculosEnUso() {
        return vehiculosEnUso;
    }

    public List<IViaje> getViajes() {
        return viajes;
    }

    public double getRecaudado() {
        return recaudado;
    }
}
