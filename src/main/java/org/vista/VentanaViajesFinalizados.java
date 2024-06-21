package org.vista;

import org.viaje.IViaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class VentanaViajesFinalizados extends JFrame {
    private JTextField[] textFields;

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

        JComboBox<IViaje> comboBox = new JComboBox<>(viajes.toArray(new IViaje[0]));
        comboBox.setBounds(50, 60, 300, 30);
        mainPanel.add(comboBox);

        String[] labels = {"ESTADO","FECHA", "CHOFER", "COSTO", "VEHICULO", "ZONA", "MASCOTA", "EQUIPAJE", "CANTIDAD PERSONAS", "DISTANCIA"};
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
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
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

        if (!viajes.isEmpty()) {
            comboBox.setSelectedIndex(0);
            updateTextFields(viajes.get(0));
        }
    }

    private void updateTextFields(IViaje viaje) {
        textFields[0].setText(viaje.getStatus());
        Date fecha = viaje.getFecha();
        textFields[1].setText(fecha.getHours() + ":"+fecha.getMinutes() + " - " + fecha.getDate() +"/"+  (fecha.getMonth()+1) +"/"+  (fecha.getYear() + 1900) );
        if (viaje.getChofer()==null)
            textFields[2].setText("Chofer Sin Asignar");
        else
            textFields[2].setText(viaje.getChofer().getNombre());

        textFields[3].setText("$ "+viaje.getCosto());
        if (viaje.getVehiculo()==null)
            textFields[4].setText("Vehiculo Sin Asignar");
        else
            textFields[4].setText(viaje.getVehiculo().getNumpatente()+" - "+viaje.getVehiculo().getTipo());

        textFields[5].setText(viaje.getZona());
        textFields[6].setText(viaje.getMascota());
        textFields[7].setText(viaje.getEquipaje());
        textFields[8].setText(String.valueOf(viaje.getCantidadPersonas()));
        textFields[9].setText(String.valueOf(viaje.getDistanciaReal())+" km");
    }
}


