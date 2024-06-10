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

    ////Cambniar esto por un hash map donde en la primera posicion este el chofer y en la segunda este si esta disponible o no
    private List<Chofer> choferes;
    private List<Chofer> choferesEnUso;
    ///*******************************************//


    ////Cambniar esto por un hash map donde en la primera posicion este el vehiculo y en la segunda este si esta disponible o no
    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosEnUso;


    ///*******************************************//

    private List<IViaje> viajes;
    private List<Pedido> pedidos;
    private double recaudado;

    public EmpresaDTO(){
    }

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


    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setViajes(List<IViaje> viajes) {
        this.viajes = viajes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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

    public void setChoferesEnUso(List<Chofer> choferesEnUso) {
        this.choferesEnUso = choferesEnUso;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }


    public List<Vehiculo> getVehiculosEnUso() {
        return vehiculosEnUso;
    }

    public void setVehiculosEnUso(List<Vehiculo> vehiculosEnUso) {
        this.vehiculosEnUso = vehiculosEnUso;
    }

    public List<IViaje> getViajes() {
        return viajes;
    }

    public double getRecaudado() {
        return recaudado;
    }

    public void setRecaudado(double recaudado) {
        this.recaudado = recaudado;
    }
}
