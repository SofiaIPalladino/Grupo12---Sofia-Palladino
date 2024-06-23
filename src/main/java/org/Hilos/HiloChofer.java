package org.hilos;

import org.chofer.Chofer;
import org.viaje.GestionViajes;
import org.viaje.GestionViajes;
import org.viaje.IViaje;

import java.util.Observable;
import java.util.Observer;

public class HiloChofer extends Thread {
    private Chofer chofer;
    private GestionViajes gestionViajes;

    public HiloChofer(Chofer chofer, GestionViajes gestionViajes) {
        this.chofer = chofer;
        this.gestionViajes = gestionViajes;
    }

    @Override
    public void run() {
        try {
            while (true) {
                IViaje viaje = gestionViajes.obtenerViajeConVehiculo(chofer);
                    synchronized (viaje) {
                        chofer.setEstado("Ocupado");
                        Thread.sleep(2000);
                        System.out.println("Estado del viaje: "+viaje.getStatus());
                        viaje.iniciarViaje(chofer);
                        gestionViajes.notificarCambios(viaje);
                        System.out.println("Chofer asignado con éxito");
                        Thread.sleep(2000);
                        System.out.println("Viaje iniciado: " + viaje);
                    }
                    realizarViaje(viaje);
                    chofer.setEstado("Libre");
                }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("ChoferThread interrumpido");
        }
    }

    private void realizarViaje(IViaje viaje) {
        synchronized (viaje) {
            viaje.notify();
            System.out.println("Entró a realizar Viaje");
            try {
                while (!viaje.getStatus().equals("Pagado")) {
                    viaje.wait();
                }
                System.out.println("El chofer " + viaje.getChofer().getNombre() + " se despertó ya que se pagó el viaje");
                Thread.sleep(2000);
                viaje.finalizarViaje();
                System.out.println("Viaje finalizado: " + viaje);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Error en realizarViaje: " + e.getMessage());
            }
            gestionViajes.finalizarViaje(viaje);
        }
        chofer.setCantidadDeViajes(chofer.getCantidadDeViajes() + 1);
        chofer.agregaKm(viaje.getDistanciaReal());
        //TODO: ACÁ VER SI SE DELEGA A LA CLASE VIAJE Y QUE HAGA TODO AHÍ O LO HACE CHOFER DESDE ESTE LADO.
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }
}
