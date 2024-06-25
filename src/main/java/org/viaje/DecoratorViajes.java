package org.viaje;

import org.chofer.Chofer;
import org.pedido.Pedido;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;

import java.io.Serializable;
import java.util.Date;

/**
 * La clase abstracta DecoratorViajes implementa la interfaz IViaje y actúa como un decorador para los objetos IViaje encapsulados.
 * Proporciona una implementación base para todas las operaciones de IViaje reenviándolas al objeto encapsulado.
 */
public abstract class DecoratorViajes implements IViaje, Serializable {

	protected IViaje encapsulado;

	public DecoratorViajes(IViaje encapsulado) {
		this.encapsulado = encapsulado;
	}

	public DecoratorViajes(){
	}

	public double getDistanciaReal() {
		return this.encapsulado.getDistanciaReal();
	}

	public Pedido getPedido() {
		return this.encapsulado.getPedido();
	}

	public void iniciarViaje(Chofer chofer) {
		this.encapsulado.iniciarViaje(chofer);
	}

	public void asignarVehiculo(Vehiculo vehiculo) {
		this.encapsulado.asignarVehiculo(vehiculo);
	}

	public void pagarViaje() {
		this.encapsulado.pagarViaje();
	}

	/**
	 * Finaliza el viaje.
	 */
	@Override
	public void finalizarViaje() {
		this.encapsulado.finalizarViaje();
	}

	/**
	 * Establece el vehículo asociado al viaje.
	 *
	 * @param vehiculo Vehículo asociado al viaje.
	 */
	public void setVehiculo(Vehiculo vehiculo) {
		this.encapsulado.setVehiculo(vehiculo);
	}

	/**
	 * Establece el estado del viaje.
	 *
	 * @param status Estado del viaje.
	 */
	public void setStatus(String status) {
		this.encapsulado.setStatus(status);
	}

	/**
	 * Establece el chofer asignado al viaje.
	 *
	 * @param chofer Chofer asignado al viaje.
	 */
	public void setChofer(Chofer chofer) {
		this.encapsulado.setChofer(chofer);
	}

	/**
	 * Obtiene el estado actual del viaje.
	 *
	 * @return Estado actual del viaje.
	 */
	public String getStatus() {
		return this.encapsulado.getStatus();
	}

	/**
	 * Obtiene el chofer asignado al viaje.
	 *
	 * @return Chofer asignado al viaje.
	 */
	public Chofer getChofer() {
		return this.encapsulado.getChofer();
	}

	/**
	 * Obtiene el vehículo asignado al viaje.
	 *
	 * @return Vehículo asignado al viaje.
	 */
	public Vehiculo getVehiculo() {
		return this.encapsulado.getVehiculo();
	}

	/**
	 * Obtiene el cliente asociado al viaje.
	 *
	 * @return Cliente asociado al viaje.
	 */
	public Cliente getCliente() {
		return this.encapsulado.getCliente();
	}

	/**
	 * Obtiene la fecha del viaje.
	 *
	 * @return Fecha del viaje.
	 */
	public Date getFecha() {
		return this.encapsulado.getFecha();
	}

	/**
	 * Devuelve una representación en cadena del objeto encapsulado.
	 *
	 * @return Representación en cadena del objeto encapsulado.
	 */
	public String toString() {
		return encapsulado.toString();
	}

	/**
	 * Obtiene la zona del viaje.
	 *
	 * @return Zona del viaje.
	 */
	public String getZona() {
		return encapsulado.getZona();
	}

	/**
	 * Obtiene la indicación si hay mascota en el viaje.
	 *
	 * @return Indicación si hay mascota en el viaje.
	 */
	public String getMascota() {
		return encapsulado.getMascota();
	}

	/**
	 * Obtiene la indicación si hay equipaje en el viaje.
	 *
	 * @return Indicación si hay equipaje en el viaje.
	 */
	public String getEquipaje() {
		return encapsulado.getEquipaje();
	}

	/**
	 * Obtiene la cantidad de personas en el viaje.
	 *
	 * @return Cantidad de personas en el viaje.
	 */
	public int getCantidadPersonas() {
		return encapsulado.getCantidadPersonas();
	}

	/**
	 * Compara este objeto con el objeto pasado como parámetro.
	 *
	 * @param viaje Objeto IViaje con el que se compara.
	 * @return Resultado de la comparación.
	 */
	public int compareTo(IViaje viaje) {
		return this.encapsulado.compareTo(viaje);
	}

	/**
	 * Realiza una copia superficial del objeto.
	 *
	 * @return Copia del objeto.
	 * @throws CloneNotSupportedException Si no es posible clonar el objeto.
	 */
	public Object clone() throws CloneNotSupportedException {
		DecoratorViajes clon = (DecoratorViajes) super.clone();
		clon.encapsulado = (IViaje) this.encapsulado.clone();
		return clon;
	}

	public IViaje getEncapsulado() {
		return encapsulado;
	}

	public void setEncapsulado(IViaje encapsulado) {
		this.encapsulado = encapsulado;
	}

}
