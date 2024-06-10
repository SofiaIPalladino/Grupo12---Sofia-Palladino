package org.vehiculo;

public class Moto extends Vehiculo {

	private static final String tipo="Moto";

	public Moto(String numpatente) {
		super(numpatente, 1, false, false);
	}

	public Moto(){

	}

	public String getTipo() {
		return "Moto";
	}

	public int califica(boolean pideBaul, int cantPax) {
		return 1000;
	}
	

}
