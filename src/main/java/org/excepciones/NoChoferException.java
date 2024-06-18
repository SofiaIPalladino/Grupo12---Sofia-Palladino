package org.excepciones;

/**
 * Excepción que se lanza cuando no se encuentra un chofer.
 */
public class NoChoferException extends Exception {
    public NoChoferException() {
        super("No hay suficientes Choferes");
    }

    public NoChoferException(String message) {
        super(message);
    }
}
