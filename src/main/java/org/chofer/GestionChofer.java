
 package org.chofer;

import org.excepciones.MaximoChoferesTipoException;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionChofer {
    private List<Chofer> choferes;
    private List<Chofer> choferesEnUso;


    public GestionChofer() {
        this.choferes=new ArrayList<>();
        this.choferesEnUso=new ArrayList<>();
    }

    private int cantidadChoferesTipo(Chofer choferTipo) {
        int count = 0;
        for (Chofer chofer : Empresa.getInstance().getChoferes()) {
            if (chofer.getClass().equals(choferTipo.getClass())) {
                count++;
            }
        }
        return count;
    }

    public void agregaChofer(Chofer c) throws MaximoChoferesTipoException {
        int cantidadChoferesMismoTipo = this.cantidadChoferesTipo(c);
        if (cantidadChoferesMismoTipo < Empresa.getInstance().getCantidadMaximaChoferesTipo())
            this.choferes.add(c);
        else
            throw new MaximoChoferesTipoException();
    }

    public List<Cliente> getClientesChofer(Chofer chofer) {
        List<IViaje> viajes = this.getViajesChofer(chofer);
        ArrayList<Cliente> clientes = new ArrayList<>();
        if (!viajes.isEmpty()) {
            Iterator<IViaje> iterator = Empresa.getInstance().getViajes().iterator();
            while (iterator.hasNext()) {
                IViaje viaje = iterator.next();
                if (!clientes.contains(viaje.getCliente()))
                    clientes.add(viaje.getCliente());
            }
        }
        return clientes;
    }

    public List<IViaje> getViajesChofer(Chofer chofer) {
        ArrayList<IViaje> viajesChofer = new ArrayList<>();
        Iterator<IViaje> iterator = Empresa.getInstance().getViajes().iterator();
        while (iterator.hasNext()) {
            IViaje viaje = iterator.next();
            if (viaje.getChofer() != null && viaje.getChofer().getDni().equals(chofer.getDni())) {
                viajesChofer.add(viaje);
            }
        }
        return viajesChofer;
    }

    public synchronized List<Chofer> getChoferes() {
        return choferes;
    }

    public List<Chofer> getChoferesEnUso() {
        return choferesEnUso;
    }

    public void setChoferesEnUso(List<Chofer> choferesEnUso) {
        this.choferesEnUso = choferesEnUso;
    }

    public void setChoferes(List<Chofer> choferes) {
        this.choferes = choferes;
    }



}
