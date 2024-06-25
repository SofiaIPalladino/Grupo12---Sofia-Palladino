package org.vehiculo;

/**
 * La clase Moto representa un tipo específico de vehículo que extiende la clase abstracta Vehiculo.
 * Implementa métodos para verificar características específicas como capacidad de pasajeros, espacio para baul, y si permite mascotas.
 */
public class Moto extends Vehiculo {

	/**
	 * Constructor que inicializa una instancia de Moto con su número de patente.
	 *
	 * @param numpatente Número de patente de la moto.
	 */
	public Moto(String numpatente) {
		super(numpatente, 1, false, false); // Llama al constructor de Vehiculo con capacidad de 1 pasajero, sin baul y sin mascota
	}

	/**
	 * Constructor necesario para la serialización.
	 */
	public Moto(){
	}

	/**
	 * Método que devuelve el tipo de vehículo, en este caso "Moto".
	 *
	 * @return Tipo de vehículo ("Moto").
	 */
	public String getTipo() {
		return "Moto";
	}

	/**
	 * Método para verificar si la moto tiene espacio para baul.
	 *
	 * @param baul Indica si se requiere espacio para baul.
	 * @return Devuelve false, ya que las motos no tienen baul.
	 */
	public boolean verificaBaul(boolean baul) {
		return !baul; // Las motos no tienen baul, por lo tanto retorna false
	}

	/**
	 * Método para verificar si la moto permite mascotas.
	 *
	 * @param mascota Indica si se viaja con mascota.
	 * @return Devuelve false, ya que las motos no permiten mascotas.
	 */
	@Override
	public boolean verificaMascota(boolean mascota) {
		return !mascota; // Las motos no permiten mascotas, por lo tanto retorna false
	}

	/**
	 * Método para verificar si la moto puede llevar cierta cantidad de pasajeros.
	 *
	 * @param cantPasajeros Cantidad de pasajeros a verificar.
	 * @return Devuelve true si la cantidad de pasajeros es igual a 1, false en caso contrario.
	 */
	public boolean verificaPasajeros(int cantPasajeros){
		return cantPasajeros == 1; // Las motos pueden llevar solo 1 pasajero
	}

	/**
	 * Método que devuelve la calificación de la moto para un pedido específico.
	 *
	 * @param pideBaul Indica si se requiere espacio para baul en el pedido.
	 * @param cantPax Cantidad de pasajeros en el pedido.
	 * @return Devuelve 1000 como la calificación para la moto, independientemente del pedido.
	 */
	public int califica(boolean pideBaul, int cantPax) {
		return 1000; // Las motos siempre tienen una calificación de 1000, independientemente del pedido
	}

}
