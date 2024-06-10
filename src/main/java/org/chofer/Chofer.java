package org.chofer;

import org.excepciones.MaximoChoferesTipoException;
import org.excepciones.NoChoferException;
import org.sistema.Empresa;
import org.viaje.IViaje;
import org.vista.VentanaChofer;

import java.io.Serializable;
import java.util.List;

/**
 * Clase abstracta que representa a un chofer de la empresa.
 * Esta clase define las características comunes para todos los choferes y declara un método abstracto para obtener el sueldo.
 */
public abstract class Chofer implements Serializable, Runnable {

    protected String dni;
    protected String nombre;
    protected int cantidadDeViajes = 0;
    protected static double aportes = 0.15;
    protected int puntaje = 0;
    protected double km = 0;
    protected String estado = "Libre";

    public Chofer() {

    }

    /**
     * Constructor para crear un Chofer.
     *
     * @param dni    El número de identificación del chofer.
     * @param nombre El nombre del chofer.
     */
    public Chofer(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    /**
     * Método abstracto para obtener el sueldo de un chofer.
     * Este método debe ser implementado por las subclases para calcular el sueldo específico de cada tipo de chofer.
     *
     * @return El sueldo del chofer.
     */
    public abstract double getSueldo();

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void sumaViaje() {
        this.cantidadDeViajes++;
    }

    public int cantViajes() {
        return this.cantidadDeViajes;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void agregaPuntaje(int puntaje) {
        this.puntaje += puntaje;
    }

    public void agregaKm(double km) {
        this.km += km;
    }

    public double getKm() {
        return km;
    }

    public synchronized void buscaViajeChofer() throws NoChoferException {
        Empresa empresa = Empresa.getInstance();
        List<IViaje> viajesSinChofer = empresa.getViajesSinChoferes();
      //  System.out.println(this.nombre );
        synchronized (empresa) {
            while (viajesSinChofer.isEmpty()) {
                //System.out.println(this.nombre+" entro al while");
                try {
                    empresa.wait(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                viajesSinChofer = empresa.getViajesSinChoferes();
            }
            IViaje viaje = viajesSinChofer.get(0);
            viaje.setChofer(this);
            viaje.setStatus("Iniciado");
            viajesSinChofer.remove(viaje);
            if (this.getClass().equals(ChoferContratado.class)) {
                ChoferContratado auxc = (ChoferContratado) this;
                auxc.recaudaDeViaje(viaje.getCosto());
            }
            this.sumaViaje();
            this.agregaKm(viaje.getPedido().getDistancia());
        }
      //  System.out.println(this.nombre+" salio del while");
        this.setEstado("En Viaje");
        System.out.println("Se asigno el chofer");
    }

    public synchronized void setEstado(String estado) {
        this.estado = estado;
        Empresa empresa = Empresa.getInstance();
        empresa.notificarCambios();
        notifyAll(); // Notificar a los hilos en espera cuando cambia el estado
    }

    public void finalizarViaje(IViaje viaje) {
        viaje.setStatus("Finalizado");
        Empresa empresa = Empresa.getInstance();
        empresa.finalizarViaje(viaje);
        this.setEstado("Libre"); // Cambiar el estado a Libre cuando el viaje finaliza
    }

    @Override
    public synchronized void run() {
        Empresa empresa = Empresa.getInstance();
        try {
            empresa.agregaChofer(this);
        } catch (MaximoChoferesTipoException e) {
            System.out.println(e.getMessage());
            return;
        }
        new VentanaChofer(this);
        empresa.agregarInformacionAccionarHilos("El chofer " + this.getNombre()+ " comenzó a trabajar.");
        while (true) {
            try {
                buscaViajeChofer();
                synchronized (this) {
                    empresa.agregarInformacionAccionarHilos("El chofer " + this.getNombre()+ " tomó un viaje.");
                    while (this.estado.equals("En Viaje")) {
                        wait(); // Esperar hasta que el estado cambie
                    }
                    empresa.agregarInformacionAccionarHilos("El chofer " + this.getNombre()+ " finalizó su viaje.");
                }
                if (empresa.getViajesChofer(this).size() == empresa.getCantidadMaximaSolicitudesPorChofer()) {
                    empresa.agregarInformacionAccionarHilos("El chofer " + this.getNombre()+ " alcanzó el máximo de viajes.");
                    empresa.quitarChofer(this);
                    break;
                }
                empresa.agregarInformacionAccionarHilos("El chofer " + this.getNombre()+ " se encuentra libre nuevamente para atender pedidos.");
            } catch (NoChoferException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void recibePago() {

    }

}
