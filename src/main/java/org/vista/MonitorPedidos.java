package org.vista;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;

public class MonitorPedidos implements Runnable {
    private ControladorGestionPedidos controlador;

    public MonitorPedidos(ControladorGestionPedidos controlador) {
        this.controlador = controlador;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!controlador.getPedidos().isEmpty()) {
                    controlador.convertirPedidosEnViajes();
                }
                Thread.sleep(5000); // Espera de 5 segundos antes de revisar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (NoVehiculoException | NoChoferException e) {
                e.printStackTrace();
            }
        }
    }
}



