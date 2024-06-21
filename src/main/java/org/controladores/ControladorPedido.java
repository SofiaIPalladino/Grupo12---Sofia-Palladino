package org.controladores;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;

import java.util.List;

public class ControladorPedido {
    private Empresa empresa;

    public ControladorPedido() {
        this.empresa = Empresa.getInstance();
    }

    public void evaluarPedido(Pedido p) throws NoVehiculoException, NoChoferException {
        empresa.evaluarPedido(p);
    }

    public void convertirPedidoEnViaje(Pedido p){
        empresa.convertirPedidoEnViaje(p);
    }


    public List<IViaje> getViajesFinalizadosCliente(Cliente cliente) {
        return empresa.getViajesClienteFinalizados(cliente);
    }
}
