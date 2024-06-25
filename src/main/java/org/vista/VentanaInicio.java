package org.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase que representa la ventana inicial de la aplicación de transporte.
 */
public class VentanaInicio extends VentanaBase {
    private ActionListener controlador;

    /**
     * Constructor de la clase VentanaInicio.
     */
    public VentanaInicio(ActionListener controlador) {
        setTitle("Subí que te llevo");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Evitar que la ventana sea redimensionable

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // Usar Layout nulo para posicionar manualmente los componentes

        // Etiqueta del título
        JLabel titleLabel = new JLabel("SUBÍ QUE TE LLEVO", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(150, 30, 300, 40); // Centrar el título horizontalmente
        mainPanel.add(titleLabel);

        // Panel para la imagen
        JPanel imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        imagePanel.setBounds(200, 90, 200, 150); // Centrar el panel de la imagen horizontalmente
        imagePanel.setLayout(new BorderLayout()); // Usar BorderLayout para centrar la imagen

        // Cargar la imagen desde un archivo (asegúrate de tener el archivo subiQueTeLlevo.png en la ruta adecuada)
        ImageIcon imageIcon = new ImageIcon("subiQueTeLlevo.png");
        JLabel imageLabel = new JLabel(imageIcon, SwingConstants.CENTER);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        mainPanel.add(imagePanel);

        // Botón para registrarse
        JButton registrarButton = new JButton("REGISTRARSE");
        registrarButton.setBounds(100, 270, 120, 30); // Posición y tamaño del botón
        mainPanel.add(registrarButton);
        registrarButton.addActionListener(controlador);

        // Botón para iniciar sesión
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(240, 270, 120, 30); // Posición y tamaño del botón
        mainPanel.add(loginButton);
        loginButton.addActionListener(controlador);

        // Botón para persistir
        JButton persistirButton = new JButton("LEVANTAR ARCHIVO");
        persistirButton.setBounds(380, 270, 120, 30); // Posición y tamaño del botón
        mainPanel.add(persistirButton);
        persistirButton.addActionListener(controlador);

        // Agregar el panel principal a la ventana
        add(mainPanel);
    }
}
