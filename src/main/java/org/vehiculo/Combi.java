package org.vehiculo;

public class Combi extends Vehiculo{

	private static final String tipo="Combi";

	public Combi(String numpatente) {
		super(numpatente, 10, false, true);
	}

	/**
	 * Constructor necesario para la serialización.
	 */
	public Combi(){
	}

	public String getTipo() {
		return "Combi";
	}

	public boolean verificaBaul(boolean baul) {
		return true;
	}

	@Override
	public boolean verificaMascota(boolean mascota) {
		return !mascota;
	}

	public boolean verificaPasajeros(int cantPasajeros){
		if (cantPasajeros <= 10)
			return true;
		else
			return false;
	}

	public int califica(boolean pideBaul, int cantPax) {
		return pideBaul ? (10*cantPax + 100) : (10*cantPax);
	}
	
}
