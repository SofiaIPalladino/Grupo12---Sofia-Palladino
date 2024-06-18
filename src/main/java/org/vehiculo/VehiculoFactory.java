package org.vehiculo;

import java.util.Objects;

/**
 * Clase que se encarga del factory para crear instancias de diferentes tipos de vehículos.
 */
public class VehiculoFactory {

	/**
	 * Crea y devuelve una instancia de vehículo según el tipo y la patente proporcionados.
	 *
	 * @param tipo    El tipo de vehículo a crear.
	 * @param patente La patente del vehículo.
	 * @return Una nueva instancia del tipo de vehículo especificado, o null si el tipo no es reconocido.
	 */
	public Vehiculo getVehiculo(String tipo,String patente) {
		if (Objects.equals(tipo, "Automovil"))
			return new Automovil(patente);
		if (Objects.equals(tipo, "Moto"))
			return new Moto(patente);
		if (Objects.equals(tipo, "Combi"))
			return new Combi(patente);
		return null;
	}
}
