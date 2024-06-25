package org.viaje;

import org.chofer.Chofer;
import org.pedido.Pedido;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;

/**
 * Clase abstracta que modela las caracteristicas y el comportamoiento de los viajes.<br>
 */
public abstract class Viaje extends Observable implements IViaje,Serializable{
	
	protected String status;
	protected Pedido pedido;
	protected Chofer chofer;
	protected Cliente cliente;
	protected double distanciaReal,costo;
	protected static double costoBase=1000;

	protected int cantPersonas;
	protected Vehiculo vehiculo;
	protected Date fecha;

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Viaje(){
	}

	public Viaje(Pedido pedido) {
		this.pedido=pedido;
		this.chofer=null;
		this.vehiculo=null;
		this.fecha = new Date();
		this.distanciaReal=pedido.getDistancia();
		this.cliente=pedido.getCliente();
		this.cantPersonas = pedido.getCantPersonas();
		this.setStatus("Solicitado");
	}

    /**
     * Este metodo setea el atributo distanciaReal con el valor recibido como parametro.<br>
     * <br> Precondicion: parametro mayor a 0.<br>
     * @param dist: nuevo valor a asignar.<br>
     */
	public void setDistanciaReal(double dist) {
		this.distanciaReal=dist;	
	}

	public static double getCostoBase() {
		return Viaje.costoBase;
	}
	
	/**
	 * Este metodo setea el atributo estatico costoBase con el valor recibido como parametro.<br>
	 * <br> Precondicion: parametro mayor a 0.<br>
	 * @param costoBase: nuevo valor a asignar.<br>
	 */
	public static void setCostoBase(double costoBase) {
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
		setChanged();
		notifyObservers();
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
		return this.cantPersonas;
	}

	/**
	 * Este metodo abstracto calculara el costo del viaje dependiento del tipo de viaje.<br>
	 */
	public abstract double getCosto();

	@Override
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
		//return "////////////Estado:" + status + " - Cliente:" + cliente.getUsuario() + " - Chofer:" + chofer +" - Vehiculo:"+ vehiculo + " - Distancia:" + distanciaReal + " km - Costo:$ " + this.getCosto();
		return fecha.getHours() + ":"+fecha.getMinutes() + " - " + fecha.getDate() +"/"+  (fecha.getMonth()+1) +"/"+  (fecha.getYear() + 1900);

	}
	@Override
	public int compareTo(IViaje viaje) {
		int rta = 0;
		if (getCosto() < viaje.getCosto())
			rta = -1;
		else if (getCosto() > viaje.getCosto())
			rta = 1;
		return rta;
	}

	public void iniciarViaje(Chofer chofer) {
		this.setChofer(chofer);
		this.setStatus("Iniciado");
		System.out.println("CAMBIO ESTADO A INICIADO");
	}

	public void asignarVehiculo(Vehiculo vehiculo){
		this.setVehiculo(vehiculo);
		this.setStatus("Con Vehículo");
		System.out.println("CAMBIO ESTADO A CON VEHÍCULO");
	}

	public void pagarViaje() {
		this.setStatus("Pagado");
		System.out.println("CAMBIO ESTADO A PAGADO");
	}

	public void finalizarViaje() {
		this.setStatus("Finalizado");
		System.out.println("CAMBIO ESTADO A FINALIZADO");
	}
	
	
	public Object clone() throws CloneNotSupportedException {
		Viaje clon= (Viaje) super.clone();
		clon.pedido = (Pedido) this.pedido.clone();
		return clon;
	}

	public int getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}
}
