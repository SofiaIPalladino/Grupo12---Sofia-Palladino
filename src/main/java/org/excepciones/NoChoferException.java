package org.excepciones;

public class NoChoferException extends Exception {
    public NoChoferException() {
        super("No hay suficientes Choferes");
    }

    public NoChoferException(String message) {
        super(message);
    }
}
