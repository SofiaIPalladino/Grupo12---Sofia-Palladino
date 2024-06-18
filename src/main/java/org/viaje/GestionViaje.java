package org.viaje;

import org.chofer.Chofer;
import org.chofer.ChoferContratado;
import org.sistema.Empresa;
import org.usuario.Cliente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionViaje {
    Empresa empresa;

    public GestionViaje(Viaje viaje) {
        this.empresa=Empresa.getInstance();
    }

    public IViaje buscarViaje(Chofer chofer) {
        List<IViaje> viajesSinChofer = this.empresa.getViajesSinChoferes();
        IViaje viaje = null;
        synchronized (viajesSinChofer) {
            if (!viajesSinChofer.isEmpty()) {
                viaje = viajesSinChofer.get(0);
                viaje.setChofer(chofer);
                viaje.setStatus("Iniciado");
                viajesSinChofer.remove(viaje);

                if (chofer.getClass().equals(ChoferContratado.class)) {
                    ChoferContratado auxc = (ChoferContratado) chofer;
                    auxc.recaudaDeViaje(viaje.getCosto());
                }
                chofer.sumaViaje();
                chofer.agregaKm(viaje.getPedido().getDistancia());
                chofer.setEstado("En Viaje");
                viajesSinChofer.notifyAll();
            }
        }
        return viaje;
    }

    public IViaje getViajeCliente(Cliente cliente) {
        Iterator<IViaje> iterator = this.empresa.getViajes().iterator();
        boolean encontre = false;
        IViaje viajeaux = null;
        while (iterator.hasNext() && !encontre) {
            IViaje viaje = iterator.next();
            if (viaje.getCliente().equals(cliente)) {
                viajeaux = viaje;
                encontre = true;
            }
        }
        return viajeaux;
    }

    public List<IViaje> getViajesClienteFinalizados(Cliente cliente) {
        ArrayList<IViaje> viajesCliente = new ArrayList<>();
        Iterator<IViaje> iterator = this.empresa.getViajes().iterator();
        //System.out.println(this.getViajes());
        while (iterator.hasNext()) {
            IViaje viaje = iterator.next();
            if (viaje.getCliente().getUsuario().equals(cliente.getUsuario()) && viaje.getStatus().equals("Finalizado")) {
                viajesCliente.add(viaje);
            }
        }
        return viajesCliente;
    }


}
