package org.vehiculo;

import java.util.Objects;

public class VehiculoFactory {
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
