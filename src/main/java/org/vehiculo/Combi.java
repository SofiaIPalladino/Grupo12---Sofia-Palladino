package org.vehiculo;

/**
 * La clase Combi representa un tipo específico de vehículo que hereda de Vehiculo.
 * Diseñado para transporte de pasajeros con capacidad extendida y ciertas características.
 */
public class Combi extends Vehiculo {

	/**
	 * Constructor que inicializa una Combi con número de patente y configura sus atributos básicos.
	 *
	 * @param numpatente Número de patente de la combi.
	 */
	public Combi(String numpatente) {
		super(numpatente, 10, false, true); // Llama al constructor de la clase padre (Vehiculo)
	}

	/**
	 * Constructor necesario para la serialización.
	 */
	public Combi() {
	}

	/**
	 * Retorna el tipo de vehículo.
	 *
	 * @return "Combi" como tipo de vehículo.
	 */
	public String getTipo() {
		return "Combi";
	}

	/**
	 * Verifica si la combi puede transportar un baul.
	 *
	 * @param baul Indica si se requiere espacio para baul (siempre true para Combi).
	 * @return Siempre retorna true porque las combis tienen espacio para baul.
	 */
	@Override
	public boolean verificaBaul(boolean baul) {
		return true;
	}

	/**
	 * Verifica si la combi puede transportar mascotas.
	 *
	 * @param mascota Indica si se desea transportar una mascota (afecta negativamente a la Combi).
	 * @return Retorna false si se desea transportar una mascota, true de lo contrario.
	 */
	@Override
	public boolean verificaMascota(boolean mascota) {
		return !mascota;
	}

	/**
	 * Verifica si la combi puede transportar cierta cantidad de pasajeros.
	 *
	 * @param cantPasajeros Cantidad de pasajeros que se desea transportar.
	 * @return true si la combi puede transportar hasta 10 pasajeros, false si excede ese límite.
	 */
	public boolean verificaPasajeros(int cantPasajeros) {
		return cantPasajeros <= 10;
	}

	/**
	 * Calcula el costo del servicio de transporte basado en la cantidad de pasajeros y si se requiere baul.
	 *
	 * @param pideBaul Indica si se requiere espacio para baul.
	 * @param cantPax  Cantidad de pasajeros que se desea transportar.
	 * @return Costo del servicio calculado en base a la cantidad de pasajeros y requerimientos adicionales.
	 */
	public int califica(boolean pideBaul, int cantPax) {
		return pideBaul ? (10 * cantPax + 100) : (10 * cantPax);
	}

}
