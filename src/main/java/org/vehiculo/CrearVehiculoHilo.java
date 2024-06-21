package org.vehiculo;

import org.controladores.ControladorPedido;
import org.excepciones.NoVehiculoException;
import org.viaje.IViaje;

public class CrearVehiculoHilo implements Runnable {
    private ControladorPedido controlador;
    private IViaje viaje;

    public CrearVehiculoHilo(ControladorPedido controlador,IViaje viaje) {
        this.controlador=controlador;
        this.viaje=viaje;
    }

    @Override
    public void run() {
        try {
            controlador.asignarVehiculo(viaje);
        } catch (NoVehiculoException e) {
            throw new RuntimeException(e);
        }
    }



}

