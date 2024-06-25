package org.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaPedido extends VentanaBase {
    private JComboBox<String> zonaComboBox;
    private JCheckBox mascotaCheckBox;
    private JCheckBox equipajeCheckBox;
    private JTextField cantPersonasField;
    private JTextField distanciaField;
    private JButton btnGuardarPedido;
    private JButton btnHistorialViajes;
    private ActionListener controlador;


    public VentanaPedido(ActionListener controlador) {
        // Configuración de la ventana
        setTitle("Ingresar Pedido");
        setSize(500, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Zona:"));
        zonaComboBox = new JComboBox<>(new String[]{"Calle sin asfaltar", "Zona Estandar", "Zona Peligrosa"});
        add(zonaComboBox);

        add(new JLabel("Mascota:"));
        mascotaCheckBox = new JCheckBox();
        add(mascotaCheckBox);

        add(new JLabel("Equipaje:"));
        equipajeCheckBox = new JCheckBox();
        add(equipajeCheckBox);

        add(new JLabel("Cantidad de Personas:"));
        cantPersonasField = new JTextField(5);
        add(cantPersonasField);

        add(new JLabel("Distancia (km):"));
        distanciaField = new JTextField(10);
        add(distanciaField);

        btnGuardarPedido = new JButton("Hacer Pedido");
        btnGuardarPedido.addActionListener(controlador);
        add(btnGuardarPedido);

        btnHistorialViajes = new JButton("Ver Historial");
        btnHistorialViajes.addActionListener(controlador);
        add(btnHistorialViajes);

        add(new JLabel(""));


    }

    public String getZona(){
        return zonaComboBox.getSelectedItem().toString();
    }

    public boolean getMascota(){
        return mascotaCheckBox.isSelected();
    }

    public boolean getEquipaje(){
        return equipajeCheckBox.isSelected();
    }

    public int getCantPersonas(){
        return Integer.parseInt(cantPersonasField.getText());
    }

    public double getDistancia(){
        return Double.parseDouble(distanciaField.getText());
    }


}
