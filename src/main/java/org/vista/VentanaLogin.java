package org.vista;

import javax.swing.*;
import java.awt.event.ActionListener;

public class VentanaLogin extends VentanaBase {
    private JLabel userLabel = new JLabel("USUARIO:");
    private JLabel passwordLabel = new JLabel("CONTRASEÑA:");
    private JTextField campoUsuario;
    private JPasswordField campoContrasenia;
    private ActionListener controlador;


    public VentanaLogin(ActionListener controlador) {
        // Configuración de la ventana
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false); // Evitar que la ventana sea redimensionable

        // Creación del panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Usar Layout nulo para posicionar manualmente los componentes


        // Etiqueta de título
        JLabel titleLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        titleLabel.setBounds(150, 20, 100, 30); // Posición y tamaño de la etiqueta
        mainPanel.add(titleLabel);

        // Agregar campos de ejemplo

        userLabel.setBounds(50, 70, 100, 30);
        mainPanel.add(userLabel);

        this.campoUsuario = new JTextField();
        this.campoUsuario.setBounds(150, 70, 200, 30);
        mainPanel.add(this.campoUsuario);

        passwordLabel.setBounds(50, 150, 100, 30);
        mainPanel.add(passwordLabel);

        this.campoContrasenia = new JPasswordField();
        this.campoContrasenia.setBounds(150, 150, 230, 30);
        this.campoContrasenia.setEnabled(true);
        mainPanel.add(this.campoContrasenia);

        // Botón Ingresar
        JButton loginButton = new JButton("INGRESAR");
        loginButton.setBounds(150, 200, 100, 30); // Posición y tamaño del botón
        loginButton.setEnabled(true);
        mainPanel.add(loginButton);
        loginButton.addActionListener(controlador);

        add(mainPanel);
    }

    public String getUsuario(){
        return campoUsuario.getText();
    }

    public String getContrasenia(){
        return campoContrasenia.getText();
    }


}
