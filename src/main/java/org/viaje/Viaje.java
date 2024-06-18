package org.viaje;


import org.chofer.Chofer;
import org.pedido.Pedido;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase abstracta que modela las caracteristicas y el comportamoiento de los viajes.<br>
 */
public abstract class Viaje implements IViaje, Serializable {
	
	protected String status;
	protected Pedido pedido;
	protected Chofer chofer;
	protected Cliente cliente;
	protected double distanciaReal,costo;
	protected static double costoBase=1000;
	protected Vehiculo vehiculo;
	protected Date fecha;


	public Viaje(){
	}

	public Viaje(Pedido pedido) {
		this.pedido=pedido;
		this.chofer=null;
		this.vehiculo=null;
		this.status="solicitado";
		this.fecha = new Date();
		this.distanciaReal=pedido.getDistancia();
		this.cliente=pedido.getCliente();
		
	}

	
    /**
     * Este metodo setea el atributo distanciaReal con el valor recibido como parametro.<br>
     * <br> Precondicion: parametro mayor a 0.<br>
     * @param dist: nuevo valor a asignar.<br>
     */
	protected void setDistanciaReal(double dist) {
		this.distanciaReal=dist;	
	}
	
	protected static double getCostoBase() {
		return Viaje.costoBase;
	}
	
	/**
	 * Este metodo setea el atributo estatico costoBase con el valor recibido como parametro.<br>
	 * <br> Precondicion: parametro mayor a 0.<br>
	 * @param costoBase: nuevo valor a asignar.<br>
	 */
	protected static void setCostoBase(double costoBase) {
		Viaje.costoBase=costoBase;
	}

	public double getDistanciaReal() {
		return this.distanciaReal;
	}
	public Pedido getPedido() {
		return pedido;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public Chofer getChofer() {
		return this.chofer;
	}

	public void setStatus(String s) {
		this.status=s;
		
	}

	public String getStatus() {
		return this.status;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public Date getFecha() {
		return this.fecha;
	}

	public String getZona(){
		return this.pedido.getZona();
	}
	public String getMascota(){
		if (this.pedido.getMascota())
			return "Si";
		else
			return "No";
	}
	public String getEquipaje(){
		if (this.pedido.usoBaul())
			return "Si";
		else
			return "No";

	}
	public int getCantidadPersonas(){
		return this.pedido.getCantPersonas();
	}

	/**
	 * Este metodo calculara el costo del viaje dependiento del tipo de viaje.<br>
	 */
	public abstract double getCosto();
	
	public String toString() {
		String vehiculo;
		String chofer;
		if (this.getChofer() == null)
			chofer = "Chofer Sin Asignar";
		else
			chofer = this.getChofer().getNombre() + "," + this.getChofer().getDni();
		if (this.getVehiculo() == null)
			vehiculo = "Vehiculo Sin Asignar";
		else
			vehiculo = this.getVehiculo().getNumpatente() + "," + this.getVehiculo().getTipo();
		return fecha.getHours() + ":"+fecha.getMinutes() + " - " + fecha.getDate() +"/"+  (fecha.getMonth()+1) +"/"+  (fecha.getYear() + 1900);

	}

	public int compareTo(IViaje viaje) {
		int rta = 0;
		if (getCosto() < viaje.getCosto())
			rta = -1;
		else if (getCosto() > viaje.getCosto())
			rta = 1;
		return rta;
	}

	public Object clone() throws CloneNotSupportedException {
		Viaje clon= (Viaje) super.clone();
		clon.pedido = (Pedido) this.pedido.clone();
		return clon;
	}

}
