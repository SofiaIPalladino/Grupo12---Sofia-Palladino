package org.vista;

import org.chofer.Chofer;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class VentanaChofer extends JFrame implements Observer {
    private Chofer chofer;
    private JPanel panelViajes;
    private JPanel panelClientes;
    private JButton botonCerrar;

    public VentanaChofer(Chofer chofer) {
        this.chofer = chofer;
        Empresa.getInstance().addObserver(this);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Chofer: " + chofer.getNombre());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Creación del panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Título
        JLabel titleLabel = new JLabel("CHOFER: " + chofer.getNombre(), SwingConstants.CENTER);
        titleLabel.setBounds(250, 20, 300, 30);
        mainPanel.add(titleLabel);

        // Panel de Viajes
        JLabel viajesLabel = new JLabel("VIAJES");
        viajesLabel.setBounds(50, 60, 100, 30);
        mainPanel.add(viajesLabel);

        panelViajes = new JPanel();
        panelViajes.setLayout(new BoxLayout(panelViajes, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneViajes = new JScrollPane(panelViajes);
        scrollPaneViajes.setBounds(50, 100, 700, 150);
        mainPanel.add(scrollPaneViajes);

        // Panel de Clientes
        JLabel clientesLabel = new JLabel("CLIENTES");
        clientesLabel.setBounds(50, 260, 100, 30);
        mainPanel.add(clientesLabel);

        panelClientes = new JPanel();
        panelClientes.setLayout(new BoxLayout(panelClientes, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneClientes = new JScrollPane(panelClientes);
        scrollPaneClientes.setBounds(50, 300, 700, 150);
        mainPanel.add(scrollPaneClientes);

        // Botón Cerrar
        botonCerrar = new JButton("CERRAR");
        botonCerrar.setBounds(350, 500, 100, 30);
        botonCerrar.addActionListener(e -> dispose());
        mainPanel.add(botonCerrar);

        add(mainPanel);
        setVisible(true);

        muestraViajes();
        muestraClientes();
    }

    private void muestraViajes() {
        List<IViaje> viajes = Empresa.getInstance().getViajesChofer(chofer);
        panelViajes.removeAll();
        if (viajes.isEmpty()) {
            panelViajes.add(new JLabel("No hay viajes disponibles"));
        } else {
            for (IViaje viaje : viajes) {
                panelViajes.add(new JLabel(informacionViaje(viaje)));
            }
        }
        panelViajes.revalidate();
        panelViajes.repaint();
    }

    private void muestraClientes() {
        List<Cliente> clientes = Empresa.getInstance().getClientesChofer(chofer);
        panelClientes.removeAll();
        if (clientes.isEmpty()) {
            panelClientes.add(new JLabel("No hay clientes disponibles"));
        } else {
            for (Cliente cliente : clientes) {
                panelClientes.add(new JLabel(informacionCliente(cliente)));
            }
        }
        panelClientes.revalidate();
        panelClientes.repaint();
    }

    private String informacionViaje(IViaje viaje) {
        StringBuilder sb = new StringBuilder();
        String vehiculo;

        if (viaje.getVehiculo() == null)
            vehiculo = "Vehiculo Sin Asignar";
        else
            vehiculo = viaje.getVehiculo().getNumpatente() + ", " + viaje.getVehiculo().getTipo();

        sb.append("Estado: ").append(viaje.getStatus()).append(" | ")
                .append("Cliente: ").append(viaje.getCliente().getNombre()).append(" | ")
                .append("Vehículo: ").append(vehiculo).append(" | ")
                .append("Distancia: ").append(viaje.getDistanciaReal()).append(" km | ")
                .append("Costo: ").append(viaje.getCosto());
        return sb.toString();
    }

    private String informacionCliente(Cliente cliente) {
        return "Nombre: " + cliente.getNombre() + " | Apellido: " + cliente.getApellido() + " | Usuario: " + cliente.getUsuario();
    }

    @Override
    public void update(Observable o, Object arg) {
        muestraViajes();
        muestraClientes();
    }
}
