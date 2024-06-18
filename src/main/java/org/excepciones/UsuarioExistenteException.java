package org.excepciones;

/**
 * Excepción que se lanza cuando se quiere registrar con un nombre de usuario ya existente.
 */
public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException() {
        super("El usuario ya existe.");
    }

    public UsuarioExistenteException(String message) {
        super(message);
        
    }
}
