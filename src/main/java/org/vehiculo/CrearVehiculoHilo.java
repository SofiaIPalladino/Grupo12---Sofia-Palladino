package org.vehiculo;

import org.controladores.ControladorGestionPedidos;
import org.excepciones.NoVehiculoException;
import org.viaje.IViaje;

public class CrearVehiculoHilo implements Runnable {
    private ControladorGestionPedidos controlador;
    private IViaje viaje;

    public CrearVehiculoHilo(ControladorGestionPedidos controladorGestionPedidos,IViaje viaje) {
        this.controlador=controladorGestionPedidos;
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

