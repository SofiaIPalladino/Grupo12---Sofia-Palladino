package org.vista;

import org.chofer.Chofer;
import org.chofer.ChoferContratado;
import org.chofer.ChoferPermanente;
import org.chofer.ChoferTemporario;
import org.excepciones.MaximoChoferesTipoException;
import org.sistema.Empresa;
import org.sistema.Fecha;
import org.usuario.Cliente;
import org.vehiculo.Automovil;
import org.vehiculo.Combi;
import org.vehiculo.Moto;

public class pruebaMVC {
    public static void main(String[] args) throws MaximoChoferesTipoException {
        Empresa empresa = Empresa.getInstance();
        Automovil auto1 = new Automovil("456624");
        Moto moto1 = new Moto("32143");
        Combi combi1 = new Combi("2325353");

        Chofer chofer1 = new ChoferTemporario("451542", "ChoferTemporario");
        Chofer chofer2 = new ChoferContratado("451352", "ChoferContratado");
        Chofer chofer3 = new ChoferPermanente("453542", "ChoferPermanente", 4, Fecha.setFecha(1, 4, 2020));

        empresa.agregaVehiculo(combi1);
        empresa.agregaVehiculo(moto1);
        empresa.agregaVehiculo(auto1);
        empresa.agregaChofer(chofer1);
        empresa.agregaChofer(chofer2);
        empresa.agregaChofer(chofer3);
        Thread hiloChofer1 = new Thread(chofer1);
        Thread hiloChofer2 = new Thread(chofer2);
        Thread hiloChofer3 = new Thread(chofer3);

        hiloChofer1.start();
        hiloChofer2.start();
        hiloChofer3.start();

        empresa.agregaUsuario("sofi", "1234", "su casa", "2413256123");
        VentanaInicio ventanaInicio = new VentanaInicio();
        ventanaInicio.setVisible(true);
        VentanaGestionPedidos ventanaGestionPedidos = new VentanaGestionPedidos();
       // VentanaPedido ventanaPedido=new VentanaPedido(empresa.getClientes().get(0));
       // VentanaCliente ventanaCLiente =new VentanaCliente();
      //  ventanaPedido.setVisible(true);
        ventanaGestionPedidos.setVisible(true);



    }
}