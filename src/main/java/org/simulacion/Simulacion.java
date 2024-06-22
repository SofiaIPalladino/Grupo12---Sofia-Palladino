package org.simulacion;

import org.hilos.HiloChofer;
import org.hilos.HiloCliente;
import org.chofer.Chofer;
import org.chofer.ChoferContratado;
import org.chofer.ChoferPermanente;
import org.chofer.ChoferTemporario;
import org.excepciones.MaximoChoferesTipoException;
import org.pedido.GestionPedidos;
import org.persistencia.EmpresaDTO;
import org.persistencia.IPersistencia;
import org.persistencia.PersistenciaXML;
import org.sistema.Empresa;
import org.sistema.Fecha;
import org.usuario.Cliente;
import org.usuario.GestionUsuario;
import org.usuario.Usuario;
import org.vehiculo.Automovil;
import org.vehiculo.Combi;
import org.vehiculo.GestionVehiculo;
import org.vehiculo.Moto;
import org.viaje.GestionViajes;

import java.io.IOException;


public class Simulacion {
    public static void main(String[] args) throws MaximoChoferesTipoException {
        Empresa empresa = Empresa.getInstance();
        GestionUsuario gestionUsuario=new GestionUsuario();
        GestionViajes gestionViajes=new GestionViajes();
        GestionPedidos gestionPedidos=new GestionPedidos();


        int cantClientes = 2;
        int cantidadUnidadesCadaTipo = 2;
        int cantidadMaximaSolicitudesCliente = 5;
        int cantidadMaximaSolicitudesChofer = 5;
        int cantidadMaximaChoferesTipo = 3;

        empresa.setCantidadMaximaSolicitudesPorCliente(cantidadMaximaSolicitudesCliente);
        empresa.setCantidadMaximaChoferesTipo(cantidadMaximaChoferesTipo);
        empresa.setCantidadMaximaSolicitudesPorChofer(cantidadMaximaSolicitudesChofer);

        //Choferes
        Chofer choferT1 = new ChoferTemporario("1234", "Chofer Temporario1");
        Chofer choferT2 = new ChoferTemporario("5678", "Chofer Temporario2");
        Chofer choferC1 = new ChoferContratado("8765", "Chofer Contratado1");
        Chofer choferC2 = new ChoferContratado("4321", "Chofer Contratado1");
        Chofer choferP1 = new ChoferPermanente("1357", "Chofer Permantente1", 4, Fecha.setFecha(10, 4, 2020));
        Chofer choferP2 = new ChoferPermanente("2468", "Chofer Permanente1", 0, Fecha.setFecha(28, 2, 2021));

        Cliente cliente1= new Cliente("sofi","dijweofj","wdqwd","swqdqw");
        //Thread hiloChofer1 = new Thread(choferT1);
        //Thread hiloChofer2 = new Thread(choferC1);
        //Thread hiloChofer3 = new Thread(choferP1);

        HiloChofer hiloChofer1= new HiloChofer(choferT1,gestionViajes);
        HiloCliente hiloCliente1= new HiloCliente(cliente1,3,gestionViajes,gestionPedidos);
        //hiloChofer1.start();
        //hiloChofer2.start();
        //hiloChofer3.start();
        //Vehiculos

        Moto moto1 = new Moto("111111");
        Moto moto2 = new Moto("222222");
        Automovil auto1 = new Automovil("333333");
        Automovil auto2 = new Automovil("444444");
        Combi combi1 = new Combi("555555");
        Combi combi2 = new Combi("666666");

        empresa.agregaVehiculo(moto2);
        empresa.agregaVehiculo(auto1);
        empresa.agregaVehiculo(auto2);
        empresa.agregaVehiculo(combi1);
        empresa.agregaVehiculo(combi2);

        //Clientes
        gestionUsuario.agregaUsuario("sofi1", "1234", "Sofia1", "Palladino");
        gestionUsuario.agregaUsuario("sofi2", "1234", "Sofia2", "Palladino");

        //  new VentanaInicio().setVisible(true);
        // new VentanaInicio().setVisible(true);
        //new VentanaGestionPedidos().setVisible(true);

        hiloCliente1.run();
        hiloChofer1.run();

        IPersistencia persistencia = new PersistenciaXML();

        try {
            try {
                persistencia.abrirOutput("empresa.xml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

          //  EmpresaDTO edto = new EmpresaDTO(e);

          //  persistencia.escribir(edto);
            persistencia.cerrarOutput();

            persistencia.abrirInput("empresa.xml");
            EmpresaDTO leoEmpresa = (EmpresaDTO) persistencia.leer();
            //e.setChoferes(edto.getChoferes());
           // e.setVehiculos(edto.getVehiculos());
            System.out.println(leoEmpresa.getChoferes());

        } catch (Exception e3) {
        }
    }
}