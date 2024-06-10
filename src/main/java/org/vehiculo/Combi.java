package org.vehiculo;

public class Combi extends Vehiculo{

	private static final String tipo="Combi";

	public Combi(String numpatente) {
		super(numpatente, 10, false, true);
	}

	public Combi(){

	}
	public String getTipo() {
		return "Combi";
	}

	public int califica(boolean pideBaul, int cantPax) {
		return pideBaul ? (10*cantPax + 100) : (10*cantPax);
	}
	
}
