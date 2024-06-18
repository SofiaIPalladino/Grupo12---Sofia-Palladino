package org.vista;

import org.controladores.ControladorPedido;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaPedido extends JFrame {
    private JComboBox<String> zonaComboBox;
    private JCheckBox mascotaCheckBox;
    private JCheckBox equipajeCheckBox;
    private JTextField cantPersonasField;
    private JTextField distanciaField;
    private JButton btnGuardarPedido;
    private JButton btnHistorialViajes;
    private ControladorPedido controlador;
    private Cliente cliente;

    public VentanaPedido(Cliente cliente) {
        setTitle("Ingresar Pedido");
        setSize(500, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.cliente = cliente;
        controlador = new ControladorPedido();

        // Configuración del layout
        setLayout(new GridLayout(7, 2, 10, 10));

        // Campos del pedido
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

        btnGuardarPedido = new JButton("Guardar Pedido");
        add(btnGuardarPedido);

        btnHistorialViajes = new JButton("Ver Historial de Viajes");
        add(btnHistorialViajes);

        add(new JLabel(""));

        btnGuardarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(cliente.getCantidadViajes());
                if (cliente.getCantidadViajes() == Empresa.getInstance().getCantidadMaximaSolicitudesPorCliente()) {
                    JOptionPane.showMessageDialog(null, "El cliente ha alcanzado el límite de solicitudes y no puede solicitar más viajes.");
                    return;
                }
                String zona = (String) zonaComboBox.getSelectedItem();
                boolean mascota = mascotaCheckBox.isSelected();
                boolean equipaje = equipajeCheckBox.isSelected();
                int cantPersonas;
                double distancia;

                try {
                    cantPersonas = Integer.parseInt(cantPersonasField.getText());
                    if (cantPersonas <= 0 || cantPersonas > 10) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número entero válido entre 1 y 10 para la cantidad de personas.");
                    return;
                }

                try {
                    distancia = Double.parseDouble(distanciaField.getText());
                    if (distancia < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número decimal válido no negativo para la distancia.");
                    return;
                }

                Pedido pedido = new Pedido(zona, mascota, equipaje ? "Baul" : "No Baul", cantPersonas, cliente, distancia);
                try {
                    controlador.evaluaPedido(pedido);
                } catch (NoVehiculoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                } catch (NoChoferException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                JOptionPane.showMessageDialog(null, "Pedido guardado exitosamente!");
                cliente.sumarViaje();
                dispose();
            }
        });

        btnHistorialViajes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               List <IViaje> viajesFinalizados = controlador.getViajesFinalizadosCliente(cliente);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        VentanaViajesFinalizados frame = new VentanaViajesFinalizados(viajesFinalizados);
                        frame.setVisible(true);
                    }
                });
            }
        });
    }
}
