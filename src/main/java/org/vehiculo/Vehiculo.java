package org.vehiculo;


import org.pedido.Pedido;


import java.io.Serializable;

/**
 * Esta clase representa un vehículo con atributos como:
 * Número de patente(numpatente).
 * Capacidad máxima de pasajeros(cantMaxPas).
 * Si admite mascotas(petFriendly).
 * Si admite baúl(baul).
 * Además, permite obtener la prioridad del vehículo para un viaje determinado mediante el método getPrioridad.
 */
public abstract class Vehiculo implements Serializable {
	protected String numpatente;
	protected int cantMaxPas;
	protected boolean petFriendly;
	protected boolean baul;
	protected String estado = "Libre";

	public Vehiculo(){

	}

	public Vehiculo(String numpatente,int cantmaxpas, boolean petfriendly, boolean baul) {
		this.numpatente=numpatente;
		this.cantMaxPas = cantmaxpas;
		this.petFriendly = petfriendly;
		this.baul = baul;
	}


	public abstract String getTipo();



	/**
	 * Este método se encarga de devolver un objeto de tipo Integer indicando el valor de prioridad para el pedido en cuestión.
	 * Se aplicó el patrón Template, por un lado tenemos métodos comúnes al algortimo que son los que están dentro de la condición del if.
	 * Luego las clases que heredan de Vehiculo deberán implementar el método abstracto califica con los detalles concretos de cada clase.
	 * 
	 * <p><strong>Precondiciones:</strong></p>
	 * <ul>
	 *   <li>El parametro pedido no puede ser nulo.</li>
	 * </ul>
	 * 
	 * <p><strong>Postcondiciones:</strong></p>
	 * <ul>
	 *   <li>El resultado siempre será un objeto de tipo Integer.</li>
	 *   <li>El resultado sera null si el vehiculo no es apto para el pedido.</li>
	 * </ul>
	 * 
	 * @param pedido Será el pedido en cuestión que está queriendo obtener el valor de prioridad del vehículo.
	 * @return El valor de prioridad o null.
	 */
	public Integer getPrioridad(Pedido pedido) {
		Integer valorPrioridad = null;
		if(verificaPasajeros(pedido.getCantPersonas()) && verificaBaul(pedido.usoBaul()) && verificaMascota(pedido.getMascota())) {
			valorPrioridad = Integer.valueOf(califica(pedido.usoBaul(), pedido.getCantPersonas()));
		}
		return valorPrioridad;
	}
	/**
	 * Determina si el vehículo tiene capacidad para los pasajeros requeridos por el pedido.
	 * 
	 * @param cantPasajeros La cantidad de pasajeros del pedido.
	 * @return un boolean.
	 */
	public boolean verificaPasajeros(int cantPasajeros){
		if (cantPasajeros <= this.cantMaxPas)
			return true;
		else
			return false;
	}
	/**
	 * Determina si el vehículo cumple con la condicion de baul del pedido.
	 * Si el pedido NO necesita baúl(baul = false) entrará por la rama del if y devolverá true ya que al no necesitar baúl cualquier vehículo cumplirá.
	 * Si el pedido SI necesita baúl(baul = true) entrará por la rama del else y devolverá true o false dependiendo de si el vehículo determinado tiene esta característica.
	 * 
	 * @param cantPasajeros La cantidad de pasajeros del pedido.
	 * @return un boolean.
	 */
	public boolean verificaBaul(boolean baul) {
		if(!baul) {
			return true;
		}else
			return this.baul;
	}
	/**
	 * idem verificaBaul
	 */
	public boolean verificaMascota(boolean mascota) {
		if(!mascota) {
			return true;
		}else
			return this.petFriendly;
	}
	
	/**
	 * Método abstracto que se encarga de calcular la calificación de prioridad que devolverá el vehículo.
	 * @param pideBaul Informa si el pedido es con baúl o no.
	 * @param cantPax Es la cantidad de pasajeros.
	 * @return un entero que es la calificación.
	 */
	protected abstract int califica(boolean pideBaul, int cantPax);

	public String getNumpatente() {
		return numpatente;
	}

	public void setNumpatente(String numpatente) {
		this.numpatente = numpatente;
	}

	public int getCantMaxPas() {
		return cantMaxPas;
	}

	public boolean isPetFriendly() {
		return petFriendly;
	}

	public boolean isBaul() {
		return baul;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return this.estado;
	}

}
