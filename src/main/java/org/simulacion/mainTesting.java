package org.simulacion;

import org.hilos.HiloChofer;
import org.hilos.HiloCliente;
import org.chofer.Chofer;
import org.chofer.ChoferContratado;
import org.chofer.ChoferPermanente;
import org.chofer.ChoferTemporario;
import org.excepciones.MaximoChoferesTipoException;
import org.sistema.Empresa;
import org.sistema.Fecha;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.vehiculo.Automovil;
import org.vehiculo.Combi;
import org.vehiculo.Moto;

public class mainTesting {
    public static void main(String[] args) throws MaximoChoferesTipoException {
        Empresa empresa = Empresa.getInstance();

        int cantClientes = 2;
        int cantidadUnidadesCadaTipo = 2;
        int cantidadMaximaSolicitudesCliente = 5;
        int cantidadMaximaSolicitudesChofer = 5;
        int cantidadMaximaChoferesTipo = 3;

        empresa.setCantidadMaximaSolicitudesPorCliente(cantidadMaximaSolicitudesCliente);
        empresa.setCantidadMaximaChoferesTipo(cantidadMaximaChoferesTipo);
        empresa.setCantidadMaximaSolicitudesPorChofer(cantidadMaximaSolicitudesChofer);

        // Choferes
        Chofer choferT1 = new ChoferTemporario("1234", "Chofer Temporario1");
        Chofer choferT2 = new ChoferTemporario("5678", "Chofer Temporario2");
        Chofer choferC1 = new ChoferContratado("8765", "Chofer Contratado1");
        Chofer choferC2 = new ChoferContratado("4321", "Chofer Contratado2"); // Nombre corregido
        Chofer choferP1 = new ChoferPermanente("1357", "Chofer Permantente1", 4, Fecha.setFecha(10, 4, 2020));
        Chofer choferP2 = new ChoferPermanente("2468", "Chofer Permanente2", 0, Fecha.setFecha(28, 2, 2021)); // Nombre corregido

        Cliente cliente1 = new Cliente("sofi", "dijweofj", "wdqwd", "swqdqw");
        Cliente cliente2 = new Cliente("maria", "abc123", "maria", "qwerty"); // Cliente adicional para pruebas

        // Hilos de Choferes
        Thread hiloChofer1 = new Thread(new HiloChofer(choferT1));
        Thread hiloChofer2 = new Thread(new HiloChofer(choferC1));
        Thread hiloChofer3 = new Thread(new HiloChofer(choferP1));

        // Hilos de Clientes
        Thread hiloCliente1 = new Thread(new HiloCliente(cliente1, 3));
        Thread hiloCliente2 = new Thread(new HiloCliente(cliente2, 2)); // Cliente adicional para pruebas

        // Vehículos
        Moto moto1 = new Moto("111111");
        Moto moto2 = new Moto("222222");
        Automovil auto1 = new Automovil("333333");
        Automovil auto2 = new Automovil("444444");
        Combi combi1 = new Combi("555555");
        Combi combi2 = new Combi("666666");

        empresa.agregaVehiculo(moto1);
        empresa.agregaVehiculo(moto2);
        empresa.agregaVehiculo(auto1);
        empresa.agregaVehiculo(auto2);
        empresa.agregaVehiculo(combi1);
        empresa.agregaVehiculo(combi2);

        // Usuarios
        empresa.agregaCliente("sofi1", "1234", "Sofia1", "Palladino");
        empresa.agregaCliente("sofi2", "1234", "Sofia2", "Palladino");

        // Inicio de hilos
        hiloCliente1.start();
        hiloCliente2.start();
        hiloChofer1.start();
        hiloChofer2.start();
        hiloChofer3.start();

        try {
            // Esperar a que los hilos terminen
            hiloCliente1.join();
            hiloCliente2.join();
            hiloChofer1.join();
            hiloChofer2.join();
            hiloChofer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
