package org.chofer;

/**
 * Clase que representa a un chofer contratado de la empresa.
 * Esta clase extiende la clase Chofer y agrega características específicas para los choferes contratados.
 */
public class ChoferContratado extends Chofer {
	
	private double recaudado;
	private static double gananciaXViaje=0.15;

	public ChoferContratado() {
	}

	/**
     * Constructor para crear un ChoferContratado.
     * @param dni El número de identificación del chofer.
     * @param nombre El nombre del chofer.
     */
	public ChoferContratado(String dni, String nombre) {
		super(dni, nombre);
 		this.recaudado = 0;
	}
	/**
	 * Método para obtener el sueldo de un chofer contratado.
	 * Este método calcula el sueldo del chofer contratado multiplicando la cantidad recaudada
	 * por el porcentaje de ganancia por viaje.
	 * @return El sueldo neto del chofer contratado.
	 */
	public double getSueldo() {
		return this.recaudado * gananciaXViaje;
	}
	
	public void recaudaDeViaje(double monto) {
		this.recaudado += monto;
	}
	public static double getGananciaXViaje() {
		return gananciaXViaje;
	}
	public static void setGananciaXViaje(double gananciaXViaje) {
		ChoferContratado.gananciaXViaje = gananciaXViaje;
	}

}
