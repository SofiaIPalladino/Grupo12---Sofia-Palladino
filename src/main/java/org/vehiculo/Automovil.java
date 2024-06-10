package org.vehiculo;

public class Automovil extends Vehiculo {

	private static final String tipo="Automovil";

	public Automovil(String numpatente) {
		super(numpatente, 4, true, true);
	}

	public Automovil(){

	}

	public String getTipo() {
		return "Automovil";
	}

	public int califica(boolean pideBaul, int cantPax) {
		return pideBaul ? (40*cantPax) : (30*cantPax);
	}

}
