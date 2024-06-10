package org.viaje;


import org.chofer.Chofer;
import org.pedido.Pedido;
import org.vehiculo.Vehiculo;

/**
 * Clase destinada a fabricar viajes.<br>
 */
public class ViajeFactory {

	/**
	 * Este metodo construye un viaje cuyo tipo depende del parametro "pedido".
	 * @param pedido: pedido por el cual se construye un viaje.<br>
	 * @return devuelve un objeto de tipo IViaje.<br>
	 * <br> Precondicion: pedido diferente de null.<br>
	 * <br> Precondicion: chofer diferente de null.<br>
	 * <br> Precondicion: vehiculo diferente de null.<br>
	 */
	public IViaje getViaje(Pedido pedido) {
		IViaje viaje;
		viaje=null;
		if(pedido.getZona().equalsIgnoreCase("Zona Estandar")) {
			viaje=new ViajeZonaEstandar(pedido);
		}
		else if(pedido.getZona().equalsIgnoreCase("Calle sin asfaltar")) {
			viaje=new ViajeZonaCalleSinAfaltar(pedido);
		}
		else if(pedido.getZona().equalsIgnoreCase("Zona Peligrosa")){
			viaje=new ViajeZonaPeligrosa(pedido);
		}
		
		if(pedido.getMascota()) {
			viaje=new DecoratorConMascota(viaje);
		}

		if(pedido.getEquipaje().equalsIgnoreCase("Baul")) {
			viaje=new DecoratorEquipajeBaul(viaje);
		}
		
		return viaje;
	}
}
