package org.controladores;

import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.viaje.IViaje;
import org.vista.VentanaPedido;
import org.vista.VentanaViajeActual;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ControladorViajeActual implements Observer {
    private Empresa empresa = Empresa.getInstance();
    private IViaje viaje;
    private VentanaViajeActual ventana;

    public ControladorViajeActual(IViaje viaje) {
        this.viaje = viaje;
        this.ventana = new VentanaViajeActual(viaje,this);
        this.ventana.setVisible(true);
        empresa.addObserver(this);
    }
    public void pagarViaje() throws ViajeNoEncontradoException {
        empresa.pagarViaje(viaje);
    }
    public void accionCerrar(){
        ventana.dispose();
        new VentanaPedido(viaje.getCliente()).setVisible(true);
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof IViaje) {
            IViaje viaje = (IViaje) arg;
            if (viaje.equals(this.viaje)) {
                ventana.actualizarEstado(viaje);
            }
        }
    }
}
