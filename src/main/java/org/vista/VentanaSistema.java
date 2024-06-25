package org.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSistema extends VentanaBase {
    private JTextArea textArea;
    private JButton persistButton;

    public VentanaSistema(ActionListener controlador) {
        setTitle("Información Hilos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(null);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Create persist button
        persistButton = new JButton("Persistir");
        persistButton.addActionListener(controlador);
        add(persistButton, BorderLayout.SOUTH);
    }

    public void mostrarInfoHilos(String newText) {
        textArea.removeAll();
        textArea.setText(newText);
        textArea.revalidate();
        textArea.repaint();
    }
    
}
