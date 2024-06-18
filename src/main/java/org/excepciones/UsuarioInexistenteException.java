package org.excepciones;

/**
 * Excepción que se lanza cuando se quiere loguear un nombre de usuario que no existe.
 */
public class UsuarioInexistenteException extends Exception {

	public UsuarioInexistenteException(String message) {
        super(message);
    }
}
