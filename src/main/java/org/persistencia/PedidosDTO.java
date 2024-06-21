package org.persistencia;

import org.pedido.Pedido;
import org.usuario.Cliente;

import java.time.LocalDate;

public class PedidosDTO {
    private String fecha;
    private String zona;
    private boolean mascota;
    private String equipaje;
    private int cantPersonas;
    private Cliente cliente;
    private double distancia;

    public PedidosDTO() {
    }

    public PedidosDTO(Pedido pedido){
        this.cantPersonas= pedido.getCantPersonas();
        this.cliente=pedido.getCliente();
        this.distancia= pedido.getDistancia();
        this.zona= pedido.getZona();
        this.equipaje= pedido.getEquipaje();
        this.mascota=pedido.getMascota();
        this.fecha=pedido.getFecha().toString();
    }

    public LocalDate getFecha() {
        return LocalDate.parse(fecha);
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public boolean isMascota() {
        return mascota;
    }

    public void setMascota(boolean mascota) {
        this.mascota = mascota;
    }

    public String getEquipaje() {
        return equipaje;
    }

    public void setEquipaje(String equipaje) {
        this.equipaje = equipaje;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}
