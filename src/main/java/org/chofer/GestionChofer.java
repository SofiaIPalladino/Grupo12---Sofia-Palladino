package org.chofer;

import org.excepciones.MaximoChoferesTipoException;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionChofer {
    Empresa empresa;

    public GestionChofer(Empresa empresa) {
        this.empresa = Empresa.getInstance();
    }

    private int cantidadChoferesTipo(Chofer choferTipo) {
        int count = 0;
        for (Chofer chofer : empresa.getChoferes()) {
            if (chofer.getClass().equals(choferTipo.getClass())) {
                count++;
            }
        }
        return count;
    }

    public void agregaChofer(Chofer c) throws MaximoChoferesTipoException {
        int cantidadChoferesMismoTipo = this.cantidadChoferesTipo(c);
        if (cantidadChoferesMismoTipo <  this.empresa.getCantidadMaximaChoferesTipo())
            this.empresa.getChoferes().add(c);
        else
            throw new MaximoChoferesTipoException();
    }

    public List<Cliente> getClientesChofer(Chofer chofer) {
        List<IViaje> viajes = this.getViajesChofer(chofer);
        ArrayList<Cliente> clientes = new ArrayList<>();
        if (!viajes.isEmpty()) {
            Iterator<IViaje> iterator = this.empresa.getViajes().iterator();
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
        Iterator<IViaje> iterator = this.empresa.getViajes().iterator();
        while (iterator.hasNext()) {
            IViaje viaje = iterator.next();
            if (viaje.getChofer() != null && viaje.getChofer().getDni().equals(chofer.getDni())) {
                viajesChofer.add(viaje);
            }
        }
        return viajesChofer;
    }

}
