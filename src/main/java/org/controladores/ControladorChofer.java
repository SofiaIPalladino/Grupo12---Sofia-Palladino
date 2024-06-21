package org.controladores;

import org.chofer.Chofer;
import org.hilos.HiloChofer;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;
import org.vista.VentanaChofer;
import java.util.*;

public class ControladorChofer implements Observer {

    private Empresa empresa;
    private Chofer chofer;
    private VentanaChofer ventana;
    private Map<Cliente, Integer> clientesChofer;

    public ControladorChofer(Empresa empresa, Chofer chofer) {
        this.empresa = empresa;
        this.chofer = chofer;
        this.clientesChofer = new HashMap<>();
        ventana = new VentanaChofer(this);
        ventana.setVisible(true);
        empresa.addObserver(this);
        iniciarHiloChofer(chofer);
    }

    public Chofer getChofer() {
        return chofer;
    }

    private void iniciarHiloChofer(Chofer chofer) {
        HiloChofer choferHilo = new HiloChofer(chofer, empresa.getGestionViajes());
        choferHilo.start();
    }

    public Map<Cliente, Integer> getClientesChofer(){
        return this.clientesChofer;
    }

    public void agregarViajeCliente(Cliente cliente){
        this.clientesChofer.put(cliente, this.clientesChofer.getOrDefault(cliente, 0) + 1);
    }

    public void agregarClienteChofer(Cliente cliente) {
        this.clientesChofer.put(cliente, 1);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof IViaje) {
            IViaje viaje = (IViaje) arg;
            if (viaje.getChofer() != null && viaje.getChofer().equals(chofer)) {
                ventana.muestraViajes(viaje);
            }
        }
    }
}
