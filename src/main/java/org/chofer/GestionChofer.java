package org.chofer;

import org.excepciones.MaximoChoferesTipoException;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionChofer implements Serializable {
    private List<Chofer> choferes;         // Lista de todos los choferes disponibles.
    private List<Chofer> choferesEnUso;    // Lista de choferes que están actualmente asignados a viajes.

    /**
     * Constructor de la clase GestionChofer.
     * Inicializa las listas de choferes disponibles y en uso.
     */
    public GestionChofer() {
        this.choferes = new ArrayList<>();
        this.choferesEnUso = new ArrayList<>();
    }

    /**
     * Método privado para contar la cantidad de choferes del mismo tipo que ya están registrados.
     *
     * @param choferTipo El tipo de chofer del que se quiere contar la cantidad.
     * @return La cantidad de choferes del mismo tipo registrados.
     */
    private int cantidadChoferesTipo(Chofer choferTipo) {
        int count = 0;
        for (Chofer chofer : Empresa.getInstance().getChoferes()) {
            if (chofer.getClass().equals(choferTipo.getClass())) {
                count++;
            }
        }
        return count;
    }

    /**
     * Método para agregar un nuevo chofer.
     *
     * @param c El chofer que se desea agregar.
     * @throws MaximoChoferesTipoException Si se excede la cantidad máxima permitida de choferes del mismo tipo.
     */
    public void agregaChofer(Chofer c) throws MaximoChoferesTipoException {
        int cantidadChoferesMismoTipo = this.cantidadChoferesTipo(c);
        if (cantidadChoferesMismoTipo < Empresa.getInstance().getCantidadMaximaChoferesTipo()) {
            this.choferes.add(c);
        } else {
            throw new MaximoChoferesTipoException();
        }
    }

    /**
     * Método para obtener los clientes asociados a un chofer dado.
     *
     * @param chofer El chofer del que se desean obtener los clientes.
     * @return Lista de clientes asociados al chofer.
     */
    public List<Cliente> getClientesChofer(Chofer chofer) {
        List<IViaje> viajes = this.getViajesChofer(chofer);
        ArrayList<Cliente> clientes = new ArrayList<>();
        if (!viajes.isEmpty()) {
            Iterator<IViaje> iterator = Empresa.getInstance().getViajes().iterator();
            while (iterator.hasNext()) {
                IViaje viaje = iterator.next();
                if (!clientes.contains(viaje.getCliente())) {
                    clientes.add(viaje.getCliente());
                }
            }
        }
        return clientes;
    }

    /**
     * Método para obtener los viajes asociados a un chofer dado.
     *
     * @param chofer El chofer del que se desean obtener los viajes.
     * @return Lista de viajes asociados al chofer.
     */
    public List<IViaje> getViajesChofer(Chofer chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<>();
        Iterator<IViaje> iterator = Empresa.getInstance().getViajes().iterator();
        System.out.println(chofer.getDni());
        while (iterator.hasNext()) {
            IViaje viaje = iterator.next();
            System.out.println(viaje.getChofer().getDni());
            if (viaje.getChofer() != null && (viaje.getChofer().compareTo(chofer) == 0)) {
                viajesChofer.add(viaje);
            }
        }
        System.out.println(viajesChofer);
        return viajesChofer;
    }

    /**
     * Método sincronizado para obtener la lista de todos los choferes disponibles.
     *
     * @return Lista de todos los choferes disponibles.
     */
    public List<Chofer> getChoferes() {
        return choferes;
    }

    /**
     * Método para obtener la lista de choferes en uso.
     *
     * @return Lista de choferes que están actualmente en uso.
     */
    public List<Chofer> getChoferesEnUso() {
        return choferesEnUso;
    }

    /**
     * Método para establecer la lista de choferes en uso.
     *
     * @param choferesEnUso La lista de choferes en uso que se desea establecer.
     */
    public void setChoferesEnUso(List<Chofer> choferesEnUso) {
        this.choferesEnUso = choferesEnUso;
    }

    /**
     * Método para establecer la lista de todos los choferes disponibles.
     *
     * @param choferes La lista de todos los choferes que se desea establecer.
     */
    public void setChoferes(List<Chofer> choferes) {
        this.choferes = choferes;
    }
}