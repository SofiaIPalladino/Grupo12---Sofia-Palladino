package org.vista;

import org.viaje.IViaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class VentanaViajesFinalizados extends VentanaBase {
    private JTextField[] textFields;
    private ActionListener controlador;
    private JComboBox<IViaje> comboBox;

    public VentanaViajesFinalizados(List<IViaje> viajes) {
        setTitle("Historial de Viajes Finalizados");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Usar Layout nulo para posicionar manualmente los componentes

        JLabel titleLabel = new JLabel("HISTORIAL VIAJES FINALIZADOS", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 30); // Posición y tamaño de la etiqueta
        mainPanel.add(titleLabel);

        this.comboBox = new JComboBox<>(viajes.toArray(new IViaje[0]));
        comboBox.setBounds(50, 60, 300, 30);
        mainPanel.add(comboBox);

        String[] labels = {"ESTADO", "FECHA", "CHOFER", "COSTO", "VEHICULO", "ZONA", "MASCOTA", "EQUIPAJE", "CANTIDAD PERSONAS", "DISTANCIA"};
        textFields = new JTextField[labels.length];

        int yPosition = 100;
        for (int i = 0; i < labels.length; i++) {
            JLabel jLabel = new JLabel(labels[i] + ":");
            jLabel.setBounds(50, yPosition, 150, 30); // Posición y tamaño de la etiqueta
            mainPanel.add(jLabel);

            JTextField textField = new JTextField();
            textField.setBounds(200, yPosition, 150, 30); // Posición y tamaño del campo de texto
            textField.setEditable(false);
            mainPanel.add(textField);
            textFields[i] = textField;

            yPosition += 30;
        }

        JButton closeButton = new JButton("CERRAR");
        closeButton.setBounds(150, 400, 100, 30); // Posición y tamaño del botón
        mainPanel.add(closeButton);
        add(mainPanel);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IViaje selectedViaje = (IViaje) comboBox.getSelectedItem();
                if (selectedViaje != null) {
                    updateTextFields(selectedViaje);
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void updateTextFields(IViaje viaje) {
        textFields[0].setText(viaje.getStatus());
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
        textFields[9].setText((viaje.getDistanciaReal()) + " km");
    }

    public void actualizarViaje(List<IViaje> historicoViajes) {
        System.out.println("actualiza lista de viajes");
        if (historicoViajes != null) {
            System.out.println("Entra al if");
            this.comboBox = new JComboBox<>(historicoViajes.toArray(new IViaje[0]));
            comboBox.setSelectedIndex(0);
            updateTextFields(historicoViajes.get(0));
            comboBox.repaint();
        }
    }
}


