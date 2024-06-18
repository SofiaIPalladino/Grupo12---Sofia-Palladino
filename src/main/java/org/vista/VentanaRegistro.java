package org.vista;

import org.controladores.ControladorVentanaRegistro;
import org.excepciones.UsuarioExistenteException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField usuarioField;
    private JPasswordField contraseniaField;
    private JButton btnRegistrar;
    private JButton btnSiguiente;
    private ControladorVentanaRegistro controlador;

    public VentanaRegistro() {
        setTitle("Ventana de Registro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        controlador = new ControladorVentanaRegistro();

        setLayout(new FlowLayout());

        add(new JLabel("Nombre:"));
        nombreField = new JTextField(20);
        add(nombreField);

        add(new JLabel("Apellido:"));
        apellidoField = new JTextField(20);
        add(apellidoField);

        add(new JLabel("Usuario:"));
        usuarioField = new JTextField(20);
        add(usuarioField);

        add(new JLabel("Contraseña:"));
        contraseniaField = new JPasswordField(20);
        add(contraseniaField);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setEnabled(false);
        add(btnRegistrar);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setEnabled(false);
        add(btnSiguiente);

        // Agregar DocumentListener a los campos de texto
        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                checkFields();
            }

            public void removeUpdate(DocumentEvent e) {
                checkFields();
            }

            public void insertUpdate(DocumentEvent e) {
                checkFields();
            }

            private void checkFields() {
                boolean allFieldsFilled = !nombreField.getText().isEmpty() && !apellidoField.getText().isEmpty() && !usuarioField.getText().isEmpty() &&
                        contraseniaField.getPassword().length > 0;
                btnRegistrar.setEnabled(allFieldsFilled);
            }
        };

        nombreField.getDocument().addDocumentListener(documentListener);
        usuarioField.getDocument().addDocumentListener(documentListener);
        apellidoField.getDocument().addDocumentListener(documentListener);
        contraseniaField.getDocument().addDocumentListener(documentListener);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String usuario = usuarioField.getText();
                String contrasenia = new String(contraseniaField.getPassword());

                try{
                    controlador.registrarUsuario(usuario, nombre, apellido, contrasenia);
                    JOptionPane.showMessageDialog(null, "Registro exitoso!");
                    VentanaInicio ventanaInicio = new VentanaInicio();
                    ventanaInicio.setVisible(true);
                    dispose();
                } catch (UsuarioExistenteException ee) {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe.");
                }
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicio ventanaInicio = new VentanaInicio();
                ventanaInicio.setVisible(true);
                dispose();
            }
        });
    }
}



