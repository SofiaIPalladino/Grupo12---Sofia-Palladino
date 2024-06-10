package org.excepciones;

public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException() {
        super("El usuario ya existe.");
    }

    public UsuarioExistenteException(String message) {
        super(message);
        
    }
}
