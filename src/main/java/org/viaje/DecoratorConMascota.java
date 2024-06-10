package org.viaje;
import org.pedido.Pedido;

/**
 * Clase concreta que modela el comportamiento de los viajes con mascota.<br>
 */
public class DecoratorConMascota extends DecoratorViajes {

	/**
	 * Construye un objeto de tipo DecoratorConMascota.<br>
	 * @param encapsulado: objeto a decorar de tipo interfaz IViaje.<br>
	 */
	public DecoratorConMascota(IViaje encapsulado) {
		super(encapsulado);
	}

	/**
	 * Este metodo modifica el costo del viaje, representado por el encapsulado, agregando un plus sobre el costo base por llevar una mascota.<br>
	 * @return devuelve un valor mayor a 0.<br>
	 */
	@Override
	public double getCosto() {
		
		double costoEncapsulado=this.encapsulado.getCosto();
		double incrXPersona=Viaje.getCostoBase()*0.10*this.encapsulado.getPedido().getCantPersonas();
		double incrXKm=Viaje.getCostoBase()*0.20*this.encapsulado.getDistanciaReal();
		
		return costoEncapsulado+incrXPersona+incrXKm;
	}

	@Override
	public Pedido getPedido() {
		return this.encapsulado.getPedido();
	}
}
