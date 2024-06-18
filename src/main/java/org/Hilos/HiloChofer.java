package org.Hilos;

import org.chofer.Chofer;
import org.excepciones.MaximoChoferesTipoException;
import org.excepciones.NoChoferException;
import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.viaje.IViaje;
import org.vista.VentanaChofer;

public class HiloChofer implements Runnable {

    private final Chofer chofer;
    private IViaje viaje;

    public HiloChofer(Chofer chofer) {
        this.chofer = chofer;
    }

    public void run() {
        Empresa empresa = Empresa.getInstance();
        boolean ejecutar = true;
        try {
            empresa.agregaChofer(this.chofer);
            empresa.agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " comenzó a trabajar.");
            while (ejecutar) {
                synchronized (empresa.getViajesSinChoferes()) {
                    while (this.chofer.getEstado().equals("Libre") && empresa.getViajesSinChoferes().isEmpty()) {
                        empresa.getViajesSinChoferes().wait();
                    }
                    if (!empresa.getViajesSinChoferes().isEmpty()) {
                        this.viaje = empresa.buscarViaje(chofer);
                    }
                }

                if (this.viaje != null) {
                    synchronized (this.viaje) {
                        this.viaje.setStatus("Iniciado");
                        empresa.agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " tomó un viaje.");
                        while (!this.viaje.getStatus().equals("Pagado")) {
                            this.viaje.wait();
                        }
                        this.chofer.finalizarViaje(this.viaje);
                        empresa.agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " finalizó su viaje.");
                    }
                    if (empresa.getViajesChofer(this.chofer).size() == empresa.getCantidadMaximaSolicitudesPorChofer()) {
                        empresa.agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " alcanzó el máximo de viajes.");
                        empresa.quitarChofer(this.chofer);
                        ejecutar = false;
                    } else
                        empresa.agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " se encuentra libre nuevamente para atender pedidos.");
                }
            }
        } catch (MaximoChoferesTipoException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
