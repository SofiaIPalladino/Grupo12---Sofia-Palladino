package org.viaje;

import org.chofer.Chofer;
import org.pedido.Pedido;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;
import java.util.Date;

public abstract class DecoratorViajes implements IViaje {

	protected IViaje encapsulado;
	
	public DecoratorViajes(IViaje encapsulado) {
		this.encapsulado=encapsulado;
	}
	
	public double getDistanciaReal() {
		return this.encapsulado.getDistanciaReal();
	}
	public Pedido getPedido() {
		return this.encapsulado.getPedido();
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.encapsulado.setVehiculo(vehiculo);
	}

	public void setStatus(String status) {
		this.encapsulado.setStatus(status);
	}

	public void setChofer(Chofer chofer) {
		this.encapsulado.setChofer(chofer);
	}

	public String getStatus() {
		return this.encapsulado.getStatus();
	}

	public Chofer getChofer() {
		return  this.encapsulado.getChofer();
	}
	
	public Vehiculo getVehiculo() {
		return this.encapsulado.getVehiculo();
	}
	
	public Cliente getCliente() {
		return this.encapsulado.getCliente();
	}
	
	public Date getFecha() {
		return this.encapsulado.getFecha();
	}

	public String toString() {
		return encapsulado.toString();
	}

	public String getZona(){
		return encapsulado.getZona();
	}
	public String getMascota(){
		return encapsulado.getMascota();
	}
	public String getEquipaje(){
		return encapsulado.getEquipaje();

	}
	public int getCantidadPersonas(){
		return encapsulado.getCantidadPersonas();
	}

	public int compareTo(IViaje viaje) {
		return this.encapsulado.compareTo(viaje);
	}
}
