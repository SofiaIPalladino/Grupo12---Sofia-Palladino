package org.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio extends JFrame {
    public VentanaInicio() {
        setTitle("Subí que te llevo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);// Centrar la ventana en la pantalla

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Usar Layout nulo para posicionar manualmente los componentes

        JLabel titleLabel = new JLabel("SUBÍ QUE TE LLEVO", SwingConstants.CENTER);
        titleLabel.setBounds(100, 20, 200, 30); // Posición y tamaño de la etiqueta
        mainPanel.add(titleLabel);


        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagePanel.setBounds(100, 70, 200, 150); // Posición y tamaño del panel de imagen
        imagePanel.setLayout(new BorderLayout()); // Usar BorderLayout para centrar la imagen

        ImageIcon imageIcon = new ImageIcon("subiQueTeLlevo.png"); // Ruta a la imagen
        JLabel imageLabel = new JLabel(imageIcon, SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(imagePanel);

        JButton registrarButton = new JButton("REGISTRARSE");
        registrarButton.setBounds(50, 230, 120, 30); // Posición y tamaño del botón
        mainPanel.add(registrarButton);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(230, 230, 120, 30); // Posición y tamaño del botón
        mainPanel.add(loginButton);

        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de registro y cerrar la actual
                VentanaRegistro ventanaRegistro = new VentanaRegistro();
                ventanaRegistro.setVisible(true);
                dispose();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de login y cerrar la actual
                VentanaLogin ventanaLogin = new VentanaLogin();
                ventanaLogin.setVisible(true);
                dispose();
            }
        });

        add(mainPanel);
    }
}
