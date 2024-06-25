package org.vista;

import org.viaje.IViaje;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;

public class VentanaViajeActual extends VentanaBase{
    private JTextField[] textFields;
    private JButton botonPaga;
    private ActionListener controlador;
    private JButton closeButton;

    public VentanaViajeActual(ActionListener controlador) {
        setTitle("Información Viaje Actual");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false); // Evitar que la ventana sea redimensionable

        // Creación del panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Usar Layout nulo para posicionar manualmente los componentes

        // Etiqueta de título
        JLabel titleLabel = new JLabel("INFORMACION VIAJE ACTUAL", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 30); // Posición y tamaño de la etiqueta
        mainPanel.add(titleLabel);

        // Crear y agregar etiquetas y campos de texto para cada campo
        String[] labels = {"ESTADO", "FECHA", "CHOFER", "COSTO", "VEHICULO", "ZONA", "MASCOTA", "EQUIPAJE", "CANTIDAD PERSONAS", "DISTANCIA"};
        textFields = new JTextField[labels.length];

        int yPosition = 100;
        for (int i = 0; i < labels.length; i++) {
            JLabel jLabel = new JLabel(labels[i] + ":");
            jLabel.setBounds(50, yPosition, 150, 30); // Posición y tamaño de la etiqueta
            mainPanel.add(jLabel);

            JTextField textField = new JTextField();
            textField.setBounds(200, yPosition, 150, 30); // Posición y tamaño del campo de texto
            textField.setEditable(false); // Hacer que el campo no sea editable
            mainPanel.add(textField);
            textFields[i] = textField;

            yPosition += 30;
        }

        // Botón Cerrar
        closeButton = new JButton("CERRAR");
        closeButton.setBounds(150, 410, 100, 30); // Posición y tamaño del botón
        closeButton.setVisible(false);
        closeButton.setEnabled(false);
        mainPanel.add(closeButton);
        closeButton.addActionListener(controlador);

        // Botón Pagar
        botonPaga = new JButton("PAGAR");
        botonPaga.setBounds(150, 410, 100, 30); // Posición y tamaño del botón
        botonPaga.setEnabled(true); // Deshabilitar por defecto si el estado no es "Iniciado"
        botonPaga.addActionListener(controlador);
        mainPanel.add(botonPaga);
        add(mainPanel);
    }


    public void actualizarViaje(IViaje viaje) {
        textFields[0].setText(viaje.getStatus());
        System.out.println("Entro a actualizar viaje de viaje act");
        Date fecha = viaje.getFecha();
        textFields[1].setText(fecha.getHours() + ":" + fecha.getMinutes() + " - " + fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + (fecha.getYear() + 1900));
        if (viaje.getChofer() == null)
            textFields[2].setText("Chofer Sin Asignar");
        else
            textFields[2].setText(viaje.getChofer().getNombre());

        textFields[3].setText("$ " + viaje.getCosto());
        if (viaje.getVehiculo() == null)
            textFields[4].setText("Vehiculo Sin Asignar");
        else
            textFields[4].setText(viaje.getVehiculo().getNumpatente() + " - " + viaje.getVehiculo().getTipo());

        textFields[5].setText(viaje.getZona());
        textFields[6].setText(viaje.getMascota());
        textFields[7].setText(viaje.getEquipaje());
        textFields[8].setText(String.valueOf(viaje.getCantidadPersonas()));
        textFields[9].setText(viaje.getDistanciaReal() + " km");
        botonPaga.setEnabled(viaje.getStatus().equals("Iniciado"));
    }

    public void setBotonVisible() {
        this.botonPaga.setVisible(false);
        this.closeButton.setVisible(true);
        this.closeButton.setEnabled(true);
    }
}
