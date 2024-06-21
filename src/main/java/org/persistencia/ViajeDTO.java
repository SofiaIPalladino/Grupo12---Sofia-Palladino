package org.persistencia;

import org.chofer.Chofer;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;
import org.viaje.Viaje;

import java.io.Serializable;
import java.time.LocalDate;

public class ViajeDTO implements Serializable {
    private String status;
    private PedidosDTO pedido;
    private Chofer chofer;
    private Cliente cliente;
    private double distanciaReal, costo;
    private static double costoBase = 1000;
    private Vehiculo vehiculo;
    private String fecha;

    public ViajeDTO(){

    }

    public ViajeDTO(Viaje viaje){
        this.status= viaje.getStatus();
        this.pedido=new PedidosDTO(viaje.getPedido());
        this.chofer=viaje.getChofer();
        this.cliente=viaje.getCliente();
        this.distanciaReal=viaje.getDistanciaReal();
        this.costo=viaje.getCosto();
        this.vehiculo= viaje.getVehiculo();
        this.fecha=viaje.getFecha().toString();
    }

    public PedidosDTO getPedido() {
        return pedido;
    }

    public LocalDate getFecha() {
        return LocalDate.parse(fecha);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public static double getCostoBase() {
        return costoBase;
    }

    public static void setCostoBase(double costoBase) {
        ViajeDTO.costoBase = costoBase;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getDistanciaReal() {
        return distanciaReal;
    }

    public void setDistanciaReal(double distanciaReal) {
        this.distanciaReal = distanciaReal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

