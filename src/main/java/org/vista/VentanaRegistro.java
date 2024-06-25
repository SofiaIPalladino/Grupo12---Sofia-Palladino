package org.vista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaRegistro extends VentanaBase {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField usuarioField;
    private JPasswordField contraseniaField;
    private JButton btnRegistrar;
    private JButton btnSiguiente;
    private ActionListener controlador;

    public VentanaRegistro(ActionListener controlador) {
        // Configuración de la ventana
        setTitle("Ventana de Registro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del layout
        setLayout(new FlowLayout());

        // Agregar campos de ejemplo
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
        btnRegistrar.setEnabled(false); // Deshabilitar el botón de registrar inicialmente
        add(btnRegistrar);
        btnRegistrar.addActionListener(controlador);

        /*
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setEnabled(false); // Deshabilitar el botón de siguiente hasta que el registro sea exitoso
        add(btnSiguiente);*/

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

    }

    public String getUsuario(){
        return usuarioField.getText();
    }

    public String getContrasenia(){
        return contraseniaField.getText();
    }

    public String getNombre(){
        return nombreField.getText();
    }

    public String getApellido(){
        return apellidoField.getText();
    }
}



