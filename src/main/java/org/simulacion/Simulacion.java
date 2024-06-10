package org.simulacion;

import org.chofer.Chofer;
import org.chofer.ChoferContratado;
import org.chofer.ChoferPermanente;
import org.chofer.ChoferTemporario;
import org.excepciones.MaximoChoferesTipoException;
import org.persistencia.EmpresaDTO;
import org.persistencia.IPersistencia;
import org.persistencia.PersistenciaXML;
import org.sistema.Empresa;
import org.sistema.Fecha;
import org.usuario.Cliente;
import org.vehiculo.Automovil;
import org.vehiculo.Combi;
import org.vehiculo.Moto;
import org.vista.VentanaChofer;
import org.vista.VentanaGestionPedidos;
import org.vista.VentanaInicio;

import java.io.IOException;


public class Simulacion {
    public static void main(String[] args) throws MaximoChoferesTipoException {
        Empresa empresa = Empresa.getInstance();


        int cantClientes = 2;
        int cantidadUnidadesCadaTipo = 2;
        int cantidadMaximaSolicitudesCliente = 1;
        int cantidadMaximaSolicitudesChofer = 1;
        int cantidadMaximaChoferesTipo = 2;

        empresa.setCantidadMaximaSolicitudesPorCliente(cantidadMaximaSolicitudesCliente);
        empresa.setCantidadMaximaChoferesTipo(cantidadMaximaChoferesTipo);
        empresa.setCantidadMaximaSolicitudesPorChofer(cantidadMaximaSolicitudesChofer);
        //Cantidad de unidades de cada tipo

        //Choferes
        Chofer choferT1 = new ChoferTemporario("1234", "Chofer Temporario1");
        Chofer choferT2 = new ChoferTemporario("5678", "Chofer Temporario2");
        Chofer choferC1 = new ChoferContratado("8765", "Chofer Contratado1");
        Chofer choferC2 = new ChoferContratado("4321", "Chofer Contratado1");
        Chofer choferP1 = new ChoferPermanente("1357", "Chofer Permantente1", 4, Fecha.setFecha(1, 4, 2020));
        Chofer choferP2 = new ChoferPermanente("2468", "Chofer Permanente1", 0, Fecha.setFecha(28, 2, 2021));

        Thread hiloChofer1 = new Thread(choferT1);
        Thread hiloChofer2 = new Thread(choferC1);
        Thread hiloChofer3 = new Thread(choferP1);

        hiloChofer1.start();
        //hiloChofer2.start();
        //hiloChofer3.start();
        //Vehiculos

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

        //Clientes
        empresa.agregaUsuario("sofi1", "1234", "Sofia1", "Palladino");
        empresa.agregaUsuario("sofi2", "1234", "Sofia2", "Palladino");

        new VentanaInicio().setVisible(true);
        new VentanaGestionPedidos().setVisible(true);

        /*
        IPersistencia persistencia = new PersistenciaXML();

        try {
            try {
                persistencia.abrirOutput("empresa.xml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            EmpresaDTO edto = new EmpresaDTO(e);

            persistencia.escribir(edto);
            persistencia.cerrarOutput();

            persistencia.abrirInput("empresa.xml");
            EmpresaDTO leoEmpresa = (EmpresaDTO) persistencia.leer();
            e.setChoferes(edto.getChoferes());
            e.setVehiculos(edto.getVehiculos());
            System.out.println(leoEmpresa.getChoferes());

        } catch (Exception e3) {
        }

         */
    }
}