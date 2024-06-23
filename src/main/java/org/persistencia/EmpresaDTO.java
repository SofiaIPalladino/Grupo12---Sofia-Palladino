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

    private List<IViaje> viajes;
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
     * @param viajes Lista de viajes.
     * @param recaudado Monto recaudado.
     */
    public EmpresaDTO(List<Cliente> clientes,List<Usuario>usuarios,List<IViaje>viajes,double recaudado) {
        this.clientes = clientes;
        this.usuarios = usuarios;
        this.viajes =viajes;
        this.recaudado = recaudado;
    }


    /**
     * Getters y setters necesarios en la clase
     */

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


    public List<IViaje> getViajes() {
        return viajes;
    }

    public double getRecaudado() {
        return recaudado;
    }
}
