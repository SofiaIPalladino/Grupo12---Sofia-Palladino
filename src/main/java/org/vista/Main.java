package org.vista;

import org.chofer.ChoferTemporario;
import org.sistema.Empresa;
import org.chofer.Chofer;
import org.usuario.Cliente;
import org.vehiculo.Automovil;
import org.vehiculo.Combi;
import org.vehiculo.Moto;
import org.viaje.IViaje;
import org.viaje.Viaje;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear chofer de prueba
        Chofer choferT1 = new ChoferTemporario("1234","Chofer Temporario1");
        Thread hiloChofer1 = new Thread(choferT1);
        hiloChofer1.start();

        // Simular obtener clientes del chofer
        Empresa empresa = Empresa.getInstance();
        empresa.agregaUsuario("sofi", "1234", "su casa", "2413256123");
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
        new VentanaInicio().setVisible(true);
        //ventanaInicio.setVisible(true);
        new VentanaGestionPedidos().setVisible(true);
        // Abrir ventana del chofer
        //SwingUtilities.invokeLater(() -> new VentanaChofer(choferT1));
    }
}