package org.chofer;

import org.viaje.IViaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa a un chofer de la empresa.
 * Esta clase define las características comunes para todos los choferes y declara un método abstracto para obtener el sueldo.
 */
public abstract class Chofer implements Serializable{

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

    public int getCantidadDeViajes() {
        return cantidadDeViajes;
    }

    public void setCantidadDeViajes(int cantidadDeViajes) {
        this.cantidadDeViajes = cantidadDeViajes;
    }

    public void recibePago() {

    }
    public void setEstado(String estado) {
        this.estado = estado;
        //notifyAll();
    }
}
