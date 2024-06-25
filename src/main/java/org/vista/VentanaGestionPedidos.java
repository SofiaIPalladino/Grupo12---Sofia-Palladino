package org.vista;

import org.viaje.IViaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la ventana de gestión de pedidos de viaje.
 */
public class VentanaGestionPedidos extends VentanaBase {
    private JPanel panelViajesActivos;
    private JPanel panelViajesFinalizados;
    private JButton botonCerrar;
    private ActionListener controlador;

    /**
     * Constructor de la clase VentanaGestionPedidos.
     *
     * @param controlador ControladorPedido que gestiona la lógica de negocio de los pedidos.
     */
    public VentanaGestionPedidos(ActionListener controlador) {
        this.controlador = controlador;
        setTitle("Gestión de Pedidos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Título
        JLabel titleLabel = new JLabel("GESTIÓN DE PEDIDOS", SwingConstants.CENTER);
        titleLabel.setBounds(250, 20, 300, 30);
        mainPanel.add(titleLabel);

        // Panel de Viajes Activos
        JLabel activosLabel = new JLabel("ACTIVOS");
        activosLabel.setBounds(50, 60, 100, 30);
        mainPanel.add(activosLabel);

        panelViajesActivos = new JPanel();
        panelViajesActivos.setLayout(new BoxLayout(panelViajesActivos, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneActivos = new JScrollPane(panelViajesActivos);
        scrollPaneActivos.setBounds(50, 100, 700, 150);
        mainPanel.add(scrollPaneActivos);

        // Panel de Viajes Finalizados
        JLabel finalizadosLabel = new JLabel("FINALIZADOS");
        finalizadosLabel.setBounds(50, 260, 100, 30);
        mainPanel.add(finalizadosLabel);

        panelViajesFinalizados = new JPanel();
        panelViajesFinalizados.setLayout(new BoxLayout(panelViajesFinalizados, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneFinalizados = new JScrollPane(panelViajesFinalizados);
        scrollPaneFinalizados.setBounds(50, 300, 700, 150);
        mainPanel.add(scrollPaneFinalizados);

        // Botón Cerrar
        botonCerrar = new JButton("CERRAR");
        botonCerrar.setBounds(350, 500, 100, 30);
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(botonCerrar);

        add(mainPanel);
    }

    /**
     * Método para actualizar el estado de los viajes mostrados en la ventana.
     *
     * @param viaje Viaje cuyo estado ha cambiado.
     */
    public void actualizarEstado(IViaje viaje) {
        if (viaje.getStatus().equals("Finalizado")) {
            agregarViajeFinalizado(viaje);
        }
        panelViajesActivos.removeAll();

        panelViajesActivos.revalidate();
        panelViajesActivos.repaint();
    }

    /**
     * Método para agregar un viaje finalizado al panel correspondiente.
     *
     * @param viaje Viaje finalizado a agregar.
     */
    private void agregarViajeFinalizado(IViaje viaje) {
        panelViajesFinalizados.add(new JLabel(informacionViaje(viaje)));
        panelViajesFinalizados.revalidate();
        panelViajesFinalizados.repaint();
    }

    /**
     * Método para obtener la información detallada de un viaje.
     *
     * @param viaje Viaje del cual obtener la información.
     * @return Cadena con la información detallada del viaje.
     */
    private String informacionViaje(IViaje viaje) {
        StringBuilder sb = new StringBuilder();
        String vehiculo;
        String chofer;

        if (viaje.getChofer() == null)
            chofer = "Chofer Sin Asignar";
        else
            chofer = viaje.getChofer().getNombre() + ", " + viaje.getChofer().getDni();

        if (viaje.getVehiculo() == null)
            vehiculo = "Vehiculo Sin Asignar";
        else
            vehiculo = viaje.getVehiculo().getNumpatente() + ", " + viaje.getVehiculo().getTipo();

        sb.append("Estado: ").append(viaje.getStatus()).append(" | ")
                .append("Cliente: ").append(viaje.getCliente().getNombre()).append(" | ")
                .append("Chofer: ").append(chofer).append(" | ")
                .append("Vehículo: ").append(vehiculo).append(" | ")
                .append("Distancia: ").append(viaje.getDistanciaReal()).append(" km | ")
                .append("Costo: ").append(viaje.getCosto());
        return sb.toString();
    }
}
