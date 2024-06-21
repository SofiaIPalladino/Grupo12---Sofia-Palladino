package org.chofer;

/**
 * Clase que representa a un chofer temporario de la empresa.
 * Esta clase extiende la clase Chofer y agrega características específicas para los choferes temporarios.
 */
public class ChoferTemporario extends Chofer {
	
	private static double sueldoBasico=15000;
	private double plusXCantViajes=0.1;
	private static int cantviajesbonus=40;


	public ChoferTemporario(){

	}


	/**
     * Constructor para crear un ChoferTemporario.
     * @param dni El número de identificación del chofer.
     * @param nombre El nombre del chofer.
     * @param sueldoBasico El sueldo básico del chofer temporario.
     * @param aportes El porcentaje de descuento por aportes jubilatorios.
     * @param plusXCantViajes El porcentaje de aumento por cantidad de viajes realizados.
     * @param cantViajes La cantidad de viajes realizados por el chofer.
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

	public double getAportes() {
		return aportes;
	}

	public double getPlusXCantViajes() {
		return plusXCantViajes;
	}

	public void setPlusXCantViajes(double plusXCantViajes) {
		this.plusXCantViajes = plusXCantViajes;
	}

	public int getCantViajes() {
		return cantidadDeViajes;
	}

	public void setCantViajes(int cantViajes) {
		this.cantidadDeViajes = cantViajes;
	}

	public double getSueldoBasico() {
		return sueldoBasico;
	}
	
	

}
