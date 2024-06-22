package org.vehiculo;

public class Automovil extends Vehiculo {

	public Automovil(String numpatente) {
		super(numpatente, 4, true, true);
	}

	/**
	 * Constructor necesario para la serialización.
	 */
	public Automovil(){
	}

	public String getTipo() {
		return "Automovil";
	}

	public boolean verificaBaul(boolean baul) {
		return true;
	}

	@Override
	public boolean verificaMascota(boolean mascota) {
		return true;
	}

	public boolean verificaPasajeros(int cantPasajeros){
		if (cantPasajeros <= 4)
			return true;
		else
			return false;
	}

	public int califica(boolean pideBaul, int cantPax) {
		return pideBaul ? (40*cantPax) : (30*cantPax);
	}

}
