package org.monitor;

import org.controladores.ControladorGestionPedidos;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;

/**
 * Clase que implementa Runnable y se encarga de monitorear y convertir pedidos en viajes.
 */
public class MonitorPedidos implements Runnable {
    private ControladorGestionPedidos controlador;

    public MonitorPedidos(ControladorGestionPedidos controlador) {
        this.controlador = controlador;
    }

    /**
     * Método run que se ejecuta en un hilo separado.
     * Monitorea continuamente los pedidos y los convierte en viajes si hay pedidos pendientes.
     *
     */
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



