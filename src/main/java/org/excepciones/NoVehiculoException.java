package org.excepciones;

/**
 * Excepción que se lanza cuando no se encuentre un vehiculo que cumpla con el requisito del pedido realizado por el cliente.
 */
public class NoVehiculoException extends Exception {
    public NoVehiculoException() {
        super("No se encontró ningún vehículo que cumpla con los requisitos del viaje.");
    }

    public NoVehiculoException(String message) {
        super(message);
        
    }
}
