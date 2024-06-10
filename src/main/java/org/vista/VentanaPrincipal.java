package org.vista;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JLabel lblBienvenida;

    public VentanaPrincipal() {
        setLayout(new FlowLayout());

        lblBienvenida = new JLabel("Subi que te llevo");
        add(lblBienvenida);

        // Agregar otros componentes a la ventana principal
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(400, 300)); // Tamaño mínimo de 400x300
        setMaximumSize(new Dimension(1024, 768));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}