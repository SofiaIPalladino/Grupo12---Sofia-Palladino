package org.sistema;


import org.chofer.Chofer;
import org.chofer.ChoferContratado;
import org.excepciones.*;
import org.usuario.Administrador;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * La clase Empresa representa la entidad principal del sistema, que gestiona
 * los clientes, usuarios, choferes, vehículos, pedidos y viajes.
 * Esta clase sigue el patrón Singleton para garantizar que solo haya una
 * instancia de la empresa en el sistema y para que pueda ser accedida desde cualquier parte del codigo.
 */

public class Empresa extends Observable {

    private static Empresa instance = null;
    private List<Cliente> clientes;
    private List<Administrador> admins;

    private List<Chofer> choferes;
    private List<Chofer> choferesEnUso;

    private List<Vehiculo> vehiculos;
    private List<Vehiculo> vehiculosEnUso;

    private List<IViaje> viajes;
    private List<IViaje> viajesSinChoferes;


    private double recaudado = 0;
    private int cantidadMaximaSolicitudesPorCliente;
    private int cantidadMaximaChoferesTipo;
    private int cantidadMaximaSolicitudesPorChofer;

    private StringBuilder informacionAccionarHilos = new StringBuilder("");


    private Empresa() {
        viajes = new ArrayList<IViaje>();
        vehiculosEnUso = new ArrayList<Vehiculo>();
        vehiculos = new ArrayList<Vehiculo>();
        choferes = new ArrayList<Chofer>();
        choferesEnUso = new ArrayList<Chofer>();
        clientes = new ArrayList<Cliente>();
        admins = new ArrayList<Administrador>();
        viajesSinChoferes = new ArrayList<IViaje>();
    }

    public static synchronized Empresa getInstance() {
        if (instance == null)
            instance = new Empresa();
        return instance;
    }

    /**
     * Metodo que se encarga de obtiene la información generada por los hilos de acción en la empresa.
     *
     * @return Un StringBuilder que contiene la información generada por los hilos de acción.
     */
    public StringBuilder getInformacionAccionarHilos() {
        return informacionAccionarHilos;
    }

    public void agregarInformacionAccionarHilos(String informacionAccionarHilos) {
        this.informacionAccionarHilos.append(informacionAccionarHilos).append("\n");
    }

    public void setCantidadMaximaSolicitudesPorCliente(int cantidadMaximaSolicitudesPorCliente) {
        this.cantidadMaximaSolicitudesPorCliente = (int) (Math.random() * cantidadMaximaSolicitudesPorCliente) + 1;
    }

    public int getCantidadMaximaSolicitudesPorCliente() {
        return this.cantidadMaximaSolicitudesPorCliente;
    }

    public void setCantidadMaximaSolicitudesPorChofer(int cantidadMaximaSolicitudesPorChofer) {
        this.cantidadMaximaSolicitudesPorChofer = (int) (Math.random() * cantidadMaximaSolicitudesPorChofer) + 1;
    }

    public int getCantidadMaximaSolicitudesPorChofer() {
        return this.cantidadMaximaSolicitudesPorChofer;
    }

    public synchronized List<IViaje> getViajesSinChoferes() {
        return viajesSinChoferes;
    }

    public void setViajesSinChoferes(IViaje viajeSinChofer) {
        this.viajesSinChoferes.add(viajeSinChofer);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public synchronized List<Chofer> getChoferes() {
        return choferes;
    }

    public synchronized List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Chofer> getChoferesEnUso() {
        return choferesEnUso;
    }


    public synchronized List<Vehiculo> getVehiculosEnUso() {
        return vehiculosEnUso;
    }

    public synchronized List<IViaje> getViajes() {
        return viajes;
    }


    public double getRecaudado() {
        return recaudado;
    }

    public void sumaRecaudado(double monto) {
        this.recaudado += monto;
    }


    public void setRecaudado(double recaudado) {
        this.recaudado = recaudado;
    }


    public void setViajes(List<IViaje> viajes) {
        this.viajes = viajes;
    }

    public void setVehiculosEnUso(List<Vehiculo> vehiculosEnUso) {
        this.vehiculosEnUso = vehiculosEnUso;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void setChoferesEnUso(List<Chofer> choferesEnUso) {
        this.choferesEnUso = choferesEnUso;
    }

    public void setChoferes(List<Chofer> choferes) {
        this.choferes = choferes;
    }

    public void setAdmins(List<Administrador> admins) {
        this.admins = admins;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public static void setInstance(Empresa instance) {
        Empresa.instance = instance;
    }

    public void setChoferConViaje(Chofer c){
        this.choferesEnUso.add(c);
        this.choferes.remove(c);
    }

    public void setChoferDisponible(Chofer c)  {
        this.choferesEnUso.remove(c);
        this.choferes.add(c);
    }

    public void setVehiculoConViaje(Vehiculo v) {
        this.vehiculosEnUso.add(v);
        this.vehiculos.remove(v);
    }

    public void setVehiculoDisponible(Vehiculo v) {
        this.vehiculosEnUso.remove(v);
        this.vehiculos.add(v);
    }

    public void agregaVehiculo(Vehiculo v) {
        this.vehiculos.add(v);
    }

    public int getCantidadMaximaChoferesTipo() {
        return cantidadMaximaChoferesTipo;
    }

    public void setCantidadMaximaChoferesTipo(int cantidadMaximaChoferesTipo) {
        this.cantidadMaximaChoferesTipo = cantidadMaximaChoferesTipo;
    }

    public void agregaUsuario(Usuario usuario){
        if (usuario.getClass().equals(Cliente.class))
            this.clientes.add((Cliente) usuario);
        else
            this.admins.add((Administrador) usuario);

    }
}


	
	
	

	
	