package org.excepciones;

/**
 * Excepción que se lanza cuando se quiere pagar un viaje no existente.
 */
public class ViajeNoEncontradoException extends Exception {
	public ViajeNoEncontradoException() {
		super("No se encontró viaje a pagar");
	}

	public ViajeNoEncontradoException(String noHayViajesDisponibles) {
		super(noHayViajesDisponibles);
	}
}
