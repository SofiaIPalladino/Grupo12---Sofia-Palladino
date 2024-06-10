package org.vista;

import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class VentanaViajeActual extends JFrame implements Observer {
    private JTextField[] textFields;
    private IViaje viaje;
    private JButton botonPaga;


    public VentanaViajeActual(IViaje viaje) {
        // Configuración de la ventana principal
        Empresa.getInstance().addObserver(this);
        this.viaje = viaje;
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
        JButton closeButton = new JButton("CERRAR");
        closeButton.setBounds(150, 410, 100, 30); // Posición y tamaño del botón
        closeButton.setVisible(false);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaPedido(viaje.getCliente()).setVisible(true);
            }
        });
        mainPanel.add(closeButton);

        // Botón Pagar
        botonPaga = new JButton("PAGAR");
        botonPaga.setBounds(150, 410, 100, 30); // Posición y tamaño del botón
        botonPaga.setEnabled(viaje.getStatus().equals("Iniciado")); // Deshabilitar por defecto si el estado no es "Iniciado"
        botonPaga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagarViajeActual();
                botonPaga.setVisible(false);
                closeButton.setVisible(true);
            }
        });

        mainPanel.add(botonPaga);

        actualizarEstado();

        // Agregar el panel principal a la ventana
        add(mainPanel);

    }

    private void pagarViajeActual() {
        Empresa empresa = Empresa.getInstance();
        empresa.finalizarViaje(viaje);
        Cliente cliente = viaje.getCliente();
        try {
            cliente.pagoViaje(viaje);
        } catch (ViajeNoEncontradoException e) {
        }
        JOptionPane.showMessageDialog(this, "Viaje pagado con éxito.");
        viaje.getChofer().finalizarViaje(viaje);
        empresa.finalizarViaje(viaje);
        System.out.println("fin PAGAR VIAJE ACTUAL");
    }

    public void update(Observable o, Object arg) {
        actualizarEstado();
    }

    public void actualizarEstado() {
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
        textFields[9].setText(String.valueOf(viaje.getDistanciaReal()) + " km");
        // Actualizar el estado del botón de pago
        botonPaga.setEnabled(viaje.getStatus().equals("Iniciado"));
    }

}
