package org.excepciones;

/**
 * Excepción que se lanza cuando se alcanza el máximo de choferes permitidos de un mismo tipo.
 */
public class MaximoChoferesTipoException extends Exception{
    public MaximoChoferesTipoException() {
        super("Se alcanzó el máximo de choferes permitidos de su mismo tipo");
    }

    public MaximoChoferesTipoException(String message) {
        super(message);
    }
}
