package org.usuario;

import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.viaje.IViaje;

import java.io.Serializable;

/**
 * Clase que representa a un cliente en el sistema.
 */
public class Cliente extends Usuario implements Serializable {

	/**
	 * Constructor para inicializar un cliente con sus datos básicos.
	 *
	 * @param usuario    Nombre de usuario del cliente.
	 * @param contrasenia Contraseña del cliente.
	 * @param nombre     Nombre del cliente.
	 * @param apellido   Apellido del cliente.
	 */
	public Cliente(String usuario, String contrasenia, String nombre, String apellido) {
		super(usuario, contrasenia, nombre, apellido);
	}

	public Cliente(){}

	/**
	 * Método para realizar el pago de un viaje por parte del cliente.
	 *
	 * @param viaje Viaje que se desea pagar.
	 * @throws ViajeNoEncontradoException Si no se encuentra el viaje especificado.
	 */
	public void pagoViaje(IViaje viaje) throws ViajeNoEncontradoException {
		synchronized (viaje) {
			// Realiza el pago del viaje
			viaje.pagarViaje();
			// Incrementa el monto recaudado por la empresa
			Empresa e = Empresa.getInstance();
			e.sumaRecaudado(viaje.getCosto());
		}
		System.out.println("Viaje pagado con éxito");
	}
}
