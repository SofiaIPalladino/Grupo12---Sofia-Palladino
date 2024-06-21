package org.controladores;

import org.sistema.Empresa;
import org.viaje.IViaje;
import org.vista.VentanaGestionPedidos;

import java.util.*;

public class ControladorGestionPedidos implements Observer {
    private Empresa empresa;
    private VentanaGestionPedidos ventana;
    private List<IViaje> viajesActivos;

    public ControladorGestionPedidos(Empresa empresa) {
        this.empresa = empresa;
        this.ventana = new VentanaGestionPedidos(this);
        this.viajesActivos = new ArrayList<>();
        empresa.addObserver(this);
        this.ventana.setVisible(true);
    }
    public List<IViaje> getViajesActivos() {
        return viajesActivos;
    }

    public void quitarViajeActivo(IViaje viaje){
        viajesActivos.remove(viaje);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof IViaje) {
            IViaje viaje = (IViaje) arg;
            if (!viajesActivos.contains(viaje)) {
                viajesActivos.add(viaje);
            }
            ventana.actualizarEstado(viaje);
        }
    }
}
    /*
    @Override
    public void update(Observable o, Object arg) {
        ventana.muestraViajes();
        ventana.muestraViajesFinalizados();
    }

     */
