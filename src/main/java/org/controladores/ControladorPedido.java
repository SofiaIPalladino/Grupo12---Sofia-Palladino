package org.controladores;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.GestionViajes;
import org.viaje.IViaje;
import org.vista.VentanaGestionPedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ControladorPedido implements Observer {
    private Empresa empresa;
    private GestionViajes gestionViajes;
    private VentanaGestionPedidos ventana;
    private List<IViaje> viajesActivos;

    public ControladorPedido() {
    	  this.empresa = Empresa.getInstance();
          this.ventana = new VentanaGestionPedidos(this);
          this.viajesActivos = new ArrayList<>();
          empresa.addObserver(this);
          this.ventana.setVisible(true);
    }

    public void evaluarPedido(Pedido p) throws NoVehiculoException, NoChoferException {
        empresa.evaluarPedido(p);
    }

    public void convertirPedidoEnViaje(Pedido p){
    	gestionViajes.convertirPedidoEnViaje(p);
    }


    public List<IViaje> getViajesFinalizadosCliente(Cliente cliente) {
        return empresa.getViajesClienteFinalizados(cliente);
    }

    public List<IViaje> getViajesActivos() {
        return viajesActivos;
    }

    public void quitarViajeActivo(IViaje viaje){
        viajesActivos.remove(viaje);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof IViaje) {
            IViaje viaje = (IViaje) arg;
            if (!viajesActivos.contains(viaje)) {
                viajesActivos.add(viaje);
            }
            ventana.actualizarEstado(viaje);
        }
    }
}
