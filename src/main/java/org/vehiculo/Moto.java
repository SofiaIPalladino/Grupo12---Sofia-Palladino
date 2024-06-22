package org.vehiculo;

public class Moto extends Vehiculo {

	public Moto(String numpatente) {
		super(numpatente, 1, false, false);
	}

	/**
	 * Constructor necesario para la serialización.
	 */
	public Moto(){
	}

	public String getTipo() {
		return "Moto";
	}

	public boolean verificaBaul(boolean baul) {
		return !baul;
	}

	@Override
	public boolean verificaMascota(boolean mascota) {
		return !mascota;
	}

	public boolean verificaPasajeros(int cantPasajeros){
		if (cantPasajeros == 1)
			return true;
		else
			return false;
	}

	public int califica(boolean pideBaul, int cantPax) {
		return 1000;
	}


}
