package org.vehiculo;

import org.pedido.Pedido;

import java.io.Serializable;

/**
 * La clase abstracta Vehiculo representa un vehículo con atributos como:
 * - Número de patente (numpatente).
 * - Capacidad máxima de pasajeros (cantMaxPas).
 * - Si admite mascotas (petFriendly).
 * - Si admite baúl (baul).
 * Además, define métodos para verificar las características del vehículo y calcular la prioridad para un pedido específico.
 */
public abstract class Vehiculo implements Serializable {
	protected String numpatente;
	protected int cantMaxPas;
	protected boolean petFriendly;
	protected boolean baul;
	protected String estado = "Libre";

	/**
	 * Constructor vacío necesario para la serialización.
	 */
	public Vehiculo() {
	}

	/**
	 * Constructor que inicializa un vehículo con sus atributos básicos.
	 *
	 * @param numpatente   Número de patente del vehículo.
	 * @param cantmaxpas   Capacidad máxima de pasajeros.
	 * @param petfriendly  Indica si el vehículo es apto para mascotas.
	 * @param baul         Indica si el vehículo tiene baúl.
	 */
	public Vehiculo(String numpatente, int cantmaxpas, boolean petfriendly, boolean baul) {
		this.numpatente = numpatente;
		this.cantMaxPas = cantmaxpas;
		this.petFriendly = petfriendly;
		this.baul = baul;
	}

	/**
	 * Método abstracto que debe ser implementado por las subclases para devolver el tipo de vehículo.
	 *
	 * @return Tipo de vehículo como cadena de texto.
	 */
	public abstract String getTipo();

	/**
	 * Método que devuelve la prioridad del vehículo para un pedido específico.
	 *
	 * @param pedido Pedido para el cual se calcula la prioridad.
	 * @return Prioridad del vehículo como un objeto Integer, o null si el vehículo no es apto para el pedido.
	 *
	 * <p><strong>Precondiciones:</strong></p>
	 * <ul>
	 *   <li>El parámetro pedido no puede ser nulo.</li>
	 * </ul>
	 *
	 * <p><strong>Postcondiciones:</strong></p>
	 * <ul>
	 *   <li>El resultado siempre será un objeto de tipo Integer.</li>
	 *   <li>El resultado será null si el vehículo no es apto para el pedido.</li>
	 * </ul>
	 */
	public Integer getPrioridad(Pedido pedido) {
		Integer valorPrioridad = null;
		if (verificaPasajeros(pedido.getCantPersonas()) && verificaBaul(pedido.usoBaul()) && verificaMascota(pedido.getMascota())) {
			valorPrioridad = Integer.valueOf(califica(pedido.usoBaul(), pedido.getCantPersonas()));
		}
		return valorPrioridad;
	}

	/**
	 * Método que verifica si el vehículo tiene capacidad para los pasajeros requeridos por el pedido.
	 *
	 * @param cantPasajeros Cantidad de pasajeros del pedido.
	 * @return true si el vehículo puede llevar la cantidad de pasajeros especificada, false en caso contrario.
	 */
	public boolean verificaPasajeros(int cantPasajeros) {
		return cantPasajeros <= this.cantMaxPas;
	}

	/**
	 * Método que verifica si el vehículo cumple con la condición de baúl del pedido.
	 *
	 * @param baul Indica si el pedido requiere baúl.
	 * @return true si el vehículo cumple con la condición de baúl del pedido, false en caso contrario.
	 */
	public boolean verificaBaul(boolean baul) {
		if (!baul) {
			return true; // No se necesita baúl, cualquier vehículo cumple
		} else {
			return this.baul; // Se necesita baúl, el vehículo debe tener baúl
		}
	}

	/**
	 * Método que verifica si el vehículo cumple con la condición de mascota del pedido.
	 *
	 * @param mascota Indica si el pedido viaja con mascota.
	 * @return true si el vehículo cumple con la condición de mascota del pedido, false en caso contrario.
	 */
	public boolean verificaMascota(boolean mascota) {
		if (!mascota) {
			return true; // No se viaja con mascota, cualquier vehículo cumple
		} else {
			return this.petFriendly; // Se viaja con mascota, el vehículo debe ser pet-friendly
		}
	}

	/**
	 * Método abstracto que debe ser implementado por las subclases para calcular la calificación de prioridad del vehículo.
	 *
	 * @param pideBaul Indica si el pedido requiere baúl.
	 * @param cantPax  Cantidad de pasajeros del pedido.
	 * @return Calificación de prioridad del vehículo como entero.
	 */
	protected abstract int califica(boolean pideBaul, int cantPax);

	/**
	 * Getter para obtener el número de patente del vehículo.
	 *
	 * @return Número de patente del vehículo como cadena de texto.
	 */
	public String getNumpatente() {
		return numpatente;
	}

	/**
	 * Setter para establecer el número de patente del vehículo.
	 *
	 * @param numpatente Número de patente del vehículo como cadena de texto.
	 */
	public void setNumpatente(String numpatente) {
		this.numpatente = numpatente;
	}

	/**
	 * Getter para obtener la capacidad máxima de pasajeros del vehículo.
	 *
	 * @return Capacidad máxima de pasajeros del vehículo como entero.
	 */
	public int getCantMaxPas() {
		return cantMaxPas;
	}

	/**
	 * Getter para obtener si el vehículo es apto para mascotas.
	 *
	 * @return true si el vehículo es pet-friendly, false en caso contrario.
	 */
	public boolean isPetFriendly() {
		return petFriendly;
	}

	/**
	 * Getter para obtener si el vehículo tiene baúl.
	 *
	 * @return true si el vehículo tiene baúl, false en caso contrario.
	 */
	public boolean isBaul() {
		return baul;
	}

	/**
	 * Setter para establecer el estado del vehículo (Libre u Ocupado).
	 *
	 * @param estado Estado del vehículo como cadena de texto ("Libre" o "Ocupado").
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Getter para obtener el estado actual del vehículo.
	 *
	 * @return Estado actual del vehículo como cadena de texto ("Libre" o "Ocupado").
	 */
	public String getEstado() {
		return this.estado;
	}

	public void setCantMaxPas(int cantMaxPas) {
		this.cantMaxPas = cantMaxPas;
	}

	public void setPetFriendly(boolean petFriendly) {
		this.petFriendly = petFriendly;
	}

	public void setBaul(boolean baul) {
		this.baul = baul;
	}

}
