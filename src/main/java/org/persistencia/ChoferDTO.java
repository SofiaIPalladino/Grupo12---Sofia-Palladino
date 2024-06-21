package org.persistencia;

import org.chofer.Chofer;
import org.chofer.ChoferContratado;
import org.chofer.ChoferPermanente;

import java.io.Serializable;
import java.time.LocalDate;

public class ChoferDTO implements Serializable {
    private String dni="dni";
    private String nombre="nombre";
    private int cantidadDeViajes=1;
    private double km=1;
    private int puntaje=1;
    private String fecha=null;
    private String tipoChofer="";


    public ChoferDTO() {
    }

    public ChoferDTO(Chofer chofer) {
        this.nombre = chofer.getNombre();
        this.cantidadDeViajes = chofer.cantViajes();
        this.dni = chofer.getDni();
        this.km = chofer.getKm();
        this.puntaje = chofer.getPuntaje();
        if (chofer instanceof ChoferPermanente) {
            this.fecha = ((ChoferPermanente) chofer).getFechaIngreso().toString();
            this.tipoChofer = "ChoferPermanente";
        } else if (chofer instanceof ChoferContratado)
            this.tipoChofer = "ChoferContratado";
        else
            this.tipoChofer = "ChoferTemporario";
    }

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

    public int getCantidadDeViajes() {
        return cantidadDeViajes;
    }

    public void setCantidadDeViajes(int cantidadDeViajes) {
        this.cantidadDeViajes = cantidadDeViajes;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoChofer() {
        return tipoChofer;
    }

    public void setTipoChofer(String tipoChofer) {
        this.tipoChofer = tipoChofer;
    }

    public LocalDate getFecha() {
        return fecha != null ? LocalDate.parse(fecha) : null;
    }
}