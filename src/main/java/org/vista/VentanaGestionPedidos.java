package org.vista;

import org.controladores.ControladorGestionPedidos;
import org.sistema.Empresa;
import org.viaje.IViaje;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class VentanaGestionPedidos extends JFrame implements Observer {
    private ControladorGestionPedidos controlador;
    private JPanel panelViajesActivos;
    private JPanel panelViajesFinalizados;
    private JPanel panelHilosActivos;
    private JButton botonCerrar;

    public VentanaGestionPedidos() {
        this.controlador = new ControladorGestionPedidos();
        Empresa.getInstance().addObserver(this);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Gestión de Pedidos");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("GESTIÓN DE PEDIDOS", SwingConstants.CENTER);
        titleLabel.setBounds(250, 20, 300, 30);
        mainPanel.add(titleLabel);

        JLabel activosLabel = new JLabel("ACTIVOS");
        activosLabel.setBounds(50, 60, 100, 30);
        mainPanel.add(activosLabel);

        panelViajesActivos = new JPanel();
        panelViajesActivos.setLayout(new BoxLayout(panelViajesActivos, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneActivos = new JScrollPane(panelViajesActivos);
        scrollPaneActivos.setBounds(50, 100, 700, 100);
        mainPanel.add(scrollPaneActivos);

        JLabel finalizadosLabel = new JLabel("FINALIZADOS");
        finalizadosLabel.setBounds(50, 210, 100, 30);
        mainPanel.add(finalizadosLabel);

        panelViajesFinalizados = new JPanel();
        panelViajesFinalizados.setLayout(new BoxLayout(panelViajesFinalizados, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneFinalizados = new JScrollPane(panelViajesFinalizados);
        scrollPaneFinalizados.setBounds(50, 250, 700, 100);
        mainPanel.add(scrollPaneFinalizados);

        // Panel de Hilos Activos
        JLabel hilosActivosLabel = new JLabel("HILOS ACTIVOS");
        hilosActivosLabel.setBounds(50, 360, 150, 30);
        mainPanel.add(hilosActivosLabel);

        panelHilosActivos = new JPanel();
        panelHilosActivos.setLayout(new BoxLayout(panelHilosActivos, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneHilosActivos = new JScrollPane(panelHilosActivos);
        scrollPaneHilosActivos.setBounds(50, 400, 700, 100);
        mainPanel.add(scrollPaneHilosActivos);

        // Botón Cerrar
        botonCerrar = new JButton("CERRAR");
        botonCerrar.setBounds(350, 550, 100, 30);
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mainPanel.add(botonCerrar);

        add(mainPanel);
        setVisible(true);

        muestraViajes();
        muestraViajesFinalizados();
        muestraHilosActivos();
    }

    private void muestraViajes() {
        List<IViaje> todosViajes = controlador.getViajes();
        panelViajesActivos.removeAll();
        if (todosViajes.isEmpty()) {
            panelViajesActivos.add(new JLabel("No hay viajes disponibles"));
        } else {
            for (IViaje viaje : todosViajes) {
                if (!viaje.getStatus().equals("Finalizado")) {
                    panelViajesActivos.add(new JLabel(informacionViaje(viaje)));
                }
            }
        }
        panelViajesActivos.revalidate();
        panelViajesActivos.repaint();
    }

    private void muestraViajesFinalizados() {
        List<IViaje> todosViajes = controlador.getViajes();
        panelViajesFinalizados.removeAll();
        if (todosViajes.isEmpty()) {
            panelViajesFinalizados.add(new JLabel("No hay viajes disponibles"));
        } else {
            for (IViaje viaje : todosViajes) {
                if (viaje.getStatus().equals("Finalizado")) {
                    panelViajesFinalizados.add(new JLabel(informacionViaje(viaje)));
                }
            }
        }
        panelViajesFinalizados.revalidate();
        panelViajesFinalizados.repaint();
    }

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

    private void muestraHilosActivos() {
        StringBuilder informacionHilos = Empresa.getInstance().getInformacionAccionarHilos();
        if (panelHilosActivos !=null) {
            panelHilosActivos.removeAll();
            String logTexto = "<html>" + informacionHilos.toString().replace("\n", "<br>") + "</html>";
            panelHilosActivos.add(new JLabel(logTexto));
            panelHilosActivos.revalidate();
            panelHilosActivos.repaint();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        muestraViajes();
        muestraViajesFinalizados();
        muestraHilosActivos();
    }
}
