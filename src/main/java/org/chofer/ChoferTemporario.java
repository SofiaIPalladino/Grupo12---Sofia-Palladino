package org.chofer;

/**
 * Clase que representa a un chofer temporario de la empresa.
 * Esta clase extiende la clase Chofer y agrega características específicas para los choferes temporarios.
 */
public class ChoferTemporario extends Chofer {
	
	private static double sueldoBasico=15000;
	private double plusXCantViajes=0.1;
	private static int cantviajesbonus=40;

	/**
	 * Constructor necesario para la serialización.
	 */
	public ChoferTemporario(){

	}


	/**
     * Constructor para crear un ChoferTemporario.
     * @param dni El número de identificación del chofer.
     * @param nombre El nombre del chofer.
     */
	public ChoferTemporario(String dni, String nombre) {	
		super(dni, nombre);
	}
	/**
     * Método para obtener el sueldo de un chofer temporario.
     * Este método calcula el sueldo bruto del chofer temporario, teniendo en cuenta su sueldo básico,
     * el plus por cantidad de viajes realizados y los descuentos por aportes jubilatorios.
     * @return El sueldo neto del chofer temporario.
     */
	@Override
	public double getSueldo() {
		double sueldo = sueldoBasico;
		if (this.cantidadDeViajes>=cantviajesbonus)
			sueldo += sueldoBasico * plusXCantViajes;
        sueldo -= sueldo * (aportes / 100);
        return sueldo;
	}
}
