package org.vista;
import org.controladores.ControladorUsuario;
import org.usuario.Cliente;
import org.usuario.Usuario;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {
    private JTextField usuarioFieldText;
    private JPasswordField campoContrasenia;
    private ControladorUsuario controlador;

    public VentanaLogin() {
        // Configuración de la ventana
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false); // Evitar que la ventana sea redimensionable


        // Inicializar el controlador
        controlador = new ControladorUsuario();

        // Creación del panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Usar Layout nulo para posicionar manualmente los componentes


        // Etiqueta de título
        JLabel titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        titleLabel.setBounds(150, 20, 100, 30); // Posición y tamaño de la etiqueta
        mainPanel.add(titleLabel);

        // Agregar campos de ejemplo
        JLabel userLabel = new JLabel("USUARIO:");
        userLabel.setBounds(50, 70, 100, 30);
        mainPanel.add(userLabel);

        JTextField campoUsuario = new JTextField();
        campoUsuario.setBounds(150, 70, 200, 30);
        mainPanel.add(campoUsuario);

        // Botón Ingresar
        JButton verificausuarioButton = new JButton("Verificar Usuario");
        verificausuarioButton.setBounds(150, 110, 140, 25); // Posición y tamaño del botón
        verificausuarioButton.setEnabled(false);
        mainPanel.add(verificausuarioButton);

        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        passwordLabel.setBounds(50, 150, 100, 30);
        mainPanel.add(passwordLabel);

        JPasswordField campoContrasenia = new JPasswordField();
        campoContrasenia.setBounds(150, 150, 230, 30);
        campoContrasenia.setEnabled(false);
        mainPanel.add(campoContrasenia);

        // Botón Ingresar
        JButton loginButton = new JButton("INGRESAR");
        loginButton.setBounds(150, 200, 100, 30); // Posición y tamaño del botón
        loginButton.setEnabled(false);
        mainPanel.add(loginButton);

        // Agregar ActionListener para el botón de verificar usuario
        verificausuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                if (controlador.validaUsuario(usuario)) {
                    JOptionPane.showMessageDialog(null, "Usuario verificado. Por favor, ingrese la contraseña.");
                    campoContrasenia.setEnabled(true);
                    loginButton.setEnabled(true);
                    campoUsuario.setEnabled(false); // Deshabilitar el campo de usuario después de la verificación
                    verificausuarioButton.setEnabled(false); // Deshabilitar el botón de verificar usuario
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario no existe.");
                }
            }
        });

        // Agregar ActionListener para el botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contrasenia = new String(campoContrasenia.getPassword());
                Usuario usuarioobj=controlador.validaContrasenia(usuario, contrasenia);
                if (usuarioobj!=null) {
                    JOptionPane.showMessageDialog(null, "Login exitoso!");
                //    controlador.agregaUsuarioLogin(usuario);
                    VentanaPedido ventanaPedido=new VentanaPedido((Cliente)usuarioobj);
                    ventanaPedido.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                }
            }
        });
        
        // Añadir listener para habilitar el botón verificar cuando se ingrese texto en el campo usuario
        campoUsuario.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                verificausuarioButton.setEnabled(!campoUsuario.getText().trim().isEmpty());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verificausuarioButton.setEnabled(!campoUsuario.getText().trim().isEmpty());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verificausuarioButton.setEnabled(!campoUsuario.getText().trim().isEmpty());
            }
        });
        add(mainPanel);
    }


}
