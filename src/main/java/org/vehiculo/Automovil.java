package org.vehiculo;

/**
 * La clase Automovil representa un tipo específico de vehículo que hereda de Vehiculo.
 * Especialmente diseñado para transporte de pasajeros con capacidad limitada y ciertas características.
 */
public class Automovil extends Vehiculo {

	/**
	 * Constructor que inicializa un Automovil con número de patente y configura sus atributos básicos.
	 *
	 * @param numpatente Número de patente del automóvil.
	 */
	public Automovil(String numpatente) {
		super(numpatente, 4, true, true); // Llama al constructor de la clase padre (Vehiculo)
	}

	/**
	 * Constructor necesario para la serialización.
	 */
	public Automovil() {
	}

	/**
	 * Retorna el tipo de vehículo.
	 *
	 * @return "Automovil" como tipo de vehículo.
	 */
	public String getTipo() {
		return "Automovil";
	}

	/**
	 * Verifica si el automóvil puede transportar un baul.
	 *
	 * @param baul Indica si se requiere espacio para baul (siempre true para Automovil).
	 * @return Siempre retorna true porque los automóviles tienen espacio para baul.
	 */
	@Override
	public boolean verificaBaul(boolean baul) {
		return true;
	}

	/**
	 * Verifica si el automóvil puede transportar mascotas.
	 *
	 * @param mascota Indica si se desea transportar una mascota (no afecta al Automovil).
	 * @return Siempre retorna true porque los automóviles pueden transportar mascotas.
	 */
	@Override
	public boolean verificaMascota(boolean mascota) {
		return true;
	}

	/**
	 * Verifica si el automóvil puede transportar cierta cantidad de pasajeros.
	 *
	 * @param cantPasajeros Cantidad de pasajeros que se desea transportar.
	 * @return true si el automóvil puede transportar hasta 4 pasajeros, false si excede ese límite.
	 */
	public boolean verificaPasajeros(int cantPasajeros) {
		return cantPasajeros <= 4;
	}

	/**
	 * Calcula el costo del servicio de transporte basado en la cantidad de pasajeros y si se requiere baul.
	 *
	 * @param pideBaul    Indica si se requiere espacio para baul.
	 * @param cantPax     Cantidad de pasajeros que se desea transportar.
	 * @return Costo del servicio calculado en base a la cantidad de pasajeros y requerimientos adicionales.
	 */
	public int califica(boolean pideBaul, int cantPax) {
		return pideBaul ? (40 * cantPax) : (30 * cantPax);
	}

}
