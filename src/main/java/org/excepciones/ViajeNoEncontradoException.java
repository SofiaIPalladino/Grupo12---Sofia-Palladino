package org.excepciones;

public class ViajeNoEncontradoException extends Exception {
	public ViajeNoEncontradoException() {
		super("No se encontró viaje a pagar");
	}
}
