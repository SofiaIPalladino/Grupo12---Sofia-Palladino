package org.excepciones;

public class NoVehiculoException extends Exception {
    public NoVehiculoException() {
        super("No se encontró ningún vehículo que cumpla con los requisitos del viaje.");
    }

    public NoVehiculoException(String message) {
        super(message);
        
    }
}
