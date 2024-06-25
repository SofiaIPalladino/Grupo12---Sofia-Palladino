package org.hilos;

import org.chofer.Chofer;
import org.sistema.Empresa;
import org.viaje.GestionViajes;
import org.viaje.GestionViajes;
import org.viaje.IViaje;

import java.util.Observable;
import java.util.Observer;

public class HiloChofer extends Thread {
    private Chofer chofer;
    private GestionViajes gestionViajes;

    public HiloChofer(Chofer chofer,GestionViajes gestionViajes) {
        this.chofer = chofer;
        this.gestionViajes= gestionViajes ;
    }

    @Override
    public void run() {
        try {
            while (true) {
                IViaje viaje = gestionViajes.obtenerViajeConVehiculo(chofer);
                Empresa.getInstance().agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " ingreso a la empresa");
                synchronized (viaje) {
                    Empresa.getInstance().agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " levantó un viaje");
                    chofer.setEstado("Ocupado");
                 //   Thread.sleep(2000);
                    System.out.println("Estado del viaje: "+viaje.getStatus());
                    viaje.iniciarViaje(chofer);
                    Empresa.getInstance().agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " inicio el viaje: "+viaje.toString());
                    gestionViajes.notificarCambios(viaje);
                    System.out.println("Chofer asignado con éxito");
                 //   Thread.sleep(2000);
                    System.out.println("Viaje iniciado: " + viaje);
                }
                realizarViaje(viaje);
                chofer.setEstado("Libre");
                Empresa.getInstance().agregarInformacionAccionarHilos("El chofer " + this.chofer.getNombre() + " esta libre para tomar un viaje");

            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("ChoferThread interrumpido");
        }
    }


    private void realizarViaje(IViaje viaje) {
        synchronized (viaje) {
            viaje.notify();
            try {
                while (!viaje.getStatus().equals("Pagado")) {
                    viaje.wait();
                }
                System.out.println("El chofer " + viaje.getChofer().getNombre() + " se despertó ya que se pagó el viaje");
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
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }
}
