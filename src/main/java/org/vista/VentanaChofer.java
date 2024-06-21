package org.vista;

import org.controladores.ControladorChofer;
import org.usuario.Cliente;
import org.viaje.IViaje;

import javax.swing.*;
import java.util.Map;

public class VentanaChofer extends JFrame {
    private JPanel panelViajes;
    private JPanel panelClientes;
    private JPanel panelViajesFinalizados;
    private JButton botonCerrar;
    private ControladorChofer controlador;

    public VentanaChofer(ControladorChofer controlador) {
        this.controlador = controlador;
        setTitle("Chofer: " + controlador.getChofer().getNombre());
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Creación del panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        // Título
        JLabel titleLabel = new JLabel("CHOFER: " + controlador.getChofer().getNombre(), SwingConstants.CENTER);
        titleLabel.setBounds(250, 20, 300, 30);
        mainPanel.add(titleLabel);

        // Panel de Viajes
        JLabel viajesLabel = new JLabel("VIAJES ACTIVOS");
        viajesLabel.setBounds(50, 60, 100, 30);
        mainPanel.add(viajesLabel);

        panelViajes = new JPanel();
        panelViajes.setLayout(new BoxLayout(panelViajes, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneViajes = new JScrollPane(panelViajes);
        scrollPaneViajes.setBounds(50, 100, 700, 150);
        mainPanel.add(scrollPaneViajes);

        // Panel de Viajes Finalizados
        JLabel viajesFinalizadosLabel = new JLabel("VIAJES FINALIZADOS");
        viajesFinalizadosLabel.setBounds(50, 260, 200, 30);
        mainPanel.add(viajesFinalizadosLabel);

        panelViajesFinalizados = new JPanel();
        panelViajesFinalizados.setLayout(new BoxLayout(panelViajesFinalizados, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneViajesFinalizados = new JScrollPane(panelViajesFinalizados);
        scrollPaneViajesFinalizados.setBounds(50, 300, 700, 150);
        mainPanel.add(scrollPaneViajesFinalizados);


        // Panel de Clientes
        JLabel clientesLabel = new JLabel("CLIENTES");
        clientesLabel.setBounds(50, 460, 100, 30);
        mainPanel.add(clientesLabel);


        panelClientes = new JPanel();
        panelClientes.setLayout(new BoxLayout(panelClientes, BoxLayout.Y_AXIS));
        JScrollPane scrollPaneClientes = new JScrollPane(panelClientes);
        scrollPaneClientes.setBounds(50, 500, 700, 150);
        mainPanel.add(scrollPaneClientes);

        // Botón Cerrar
        botonCerrar = new JButton("CERRAR");
        botonCerrar.setBounds(350, 660, 100, 30);
        botonCerrar.addActionListener(e -> dispose());
        mainPanel.add(botonCerrar);

        add(mainPanel);
    }

    private void agregaViajeFinalizado(IViaje viaje){
        panelViajesFinalizados.add(new JLabel(informacionViaje(viaje)));
        agregaCliente(viaje.getCliente());
        panelViajesFinalizados.revalidate();
        panelViajesFinalizados.repaint();
    }

    public void muestraViajes(IViaje viaje) {
        panelViajes.removeAll();
        if (viaje.getStatus().equals("Finalizado"))
            agregaViajeFinalizado(viaje);
        else
            panelViajes.add(new JLabel(informacionViaje(viaje)));
        panelViajes.revalidate();
        panelViajes.repaint();
    }

    private void agregaCliente(Cliente cliente){
        if (!controlador.getClientesChofer().containsKey(cliente)) {
            controlador.agregarClienteChofer(cliente);
        }
        else
            controlador.agregarViajeCliente(cliente);
        panelClientes.removeAll();
        for (Map.Entry<Cliente, Integer> entry : controlador.getClientesChofer().entrySet()) {
            Cliente clienteHashMap = entry.getKey();
            Integer cantidadViajes = entry.getValue();
            panelClientes.add(new JLabel(informacionCliente(clienteHashMap) + " - Cantidad de Viajes: " + cantidadViajes));
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

}
