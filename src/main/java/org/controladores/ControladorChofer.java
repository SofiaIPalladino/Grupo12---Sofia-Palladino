package org.controladores;

import org.chofer.Chofer;
import org.hilos.HiloChofer;
import org.persistencia.PersistirDatos;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;
import org.vista.VentanaChofer;

import java.io.IOException;
import java.util.*;

public class ControladorChofer implements Observer {

    private Chofer chofer;
    private VentanaChofer ventana;
    private Map<Cliente, Integer> clientesChofer;
    private List<IViaje> viajesFinalizados;
    private HiloChofer hiloChofer=null;

    /**
     * Constructor de la clase ControladorChofer.
     *
     * @param chofer El chofer asociado al controlador.
     *               Precondición: chofer no debe ser null.
     */
    public ControladorChofer(Chofer chofer) {
        this.chofer = chofer;
        this.clientesChofer = new HashMap<>();
        ventana = new VentanaChofer(chofer.getNombre());
        ventana.setVisible(true);
        Empresa.getInstance().addObserver(this);
        hiloChofer=new HiloChofer(this.chofer,Empresa.getInstance().getGestionViajes());
        hiloChofer.start();
        viajesFinalizados = Empresa.getInstance().getGestionChofer().getViajesChofer(chofer);
        for (IViaje viaje : viajesFinalizados){
            Cliente cliente = viaje.getCliente();
            if (!this.getClientesChofer().containsKey(cliente)) {
                this.agregarClienteChofer(cliente);
            }
            else
                this.agregarViajeCliente(cliente);
        }
        ventana.actualizarClientes(clientesChofer);
    }

    /**
     * Método para obtener el chofer asociado a este controlador.
     *
     * @return El chofer asociado.
     */
    public Chofer getChofer() {
        return chofer;
    }

    /**
     * Método para obtener el mapa de clientes asignados al chofer.
     *
     * @return Mapa de clientes con la cantidad de viajes asignados.
     */
    public Map<Cliente, Integer> getClientesChofer() {
        return this.clientesChofer;
    }


    /**
     * Método para agregar un viaje a un cliente específico del chofer.
     * Incrementa el contador de viajes para el cliente dado.
     *
     * @param cliente El cliente al que se le asigna un nuevo viaje.
     *                Precondición: cliente no debe ser null.
     */
    public void agregarViajeCliente(Cliente cliente) {
        this.clientesChofer.put(cliente, this.clientesChofer.getOrDefault(cliente, 0) + 1);
    }

    /**
     * Método para agregar un nuevo cliente al chofer.
     * Inicializa el contador de viajes para el nuevo cliente.
     *
     * @param cliente El nuevo cliente a ser asignado al chofer.
     *                Precondición: cliente no debe ser null.
     */
    public void agregarClienteChofer(Cliente cliente) {
        this.clientesChofer.put(cliente, 1);
    }


    /**
     * Método de la interfaz Observer que se llama cuando hay actualizaciones
     * en el objeto observado (Empresa).
     * Muestra los viajes en la ventana del chofer si el viaje involucra a este chofer.
     *
     * @param o   El objeto Observable que ha cambiado (en este caso, Empresa).
     * @param arg El argumento pasado al método update (en este caso, IViaje).
     *            Precondición: o debe ser una instancia de Empresa y arg debe ser una instancia de IViaje.
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof IViaje) {
            IViaje viaje = (IViaje) arg;
            if (viaje.getChofer() != null && viaje.getChofer().equals(chofer)) {
                if (viaje.getStatus().equals("Finalizado")){
                    Cliente cliente = viaje.getCliente();
                    if (!this.getClientesChofer().containsKey(cliente)) {
                        this.agregarClienteChofer(cliente);
                    }
                    else
                        this.agregarViajeCliente(cliente);
                    ventana.agregaViajeFinalizado(viaje);
                    ventana.actualizarClientes(clientesChofer);
                }
                ventana.muestraViajes(viaje);
            }
        }
    }

}