package org.controladores;

import org.chofer.Chofer;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;
import org.vista.VentanaLogin;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la gestión de pedidos.
 * Evalúa pedidos, busca vehículos y choferes disponibles, y gestiona los viajes finalizados.
 */
public class ControladorPedido {
    private Empresa empresa;
    private VentanaLogin vista;
    private Usuario model;

    public ControladorPedido() {
        this.empresa = Empresa.getInstance();
    }


    /**
     * Metodo que se encarga de obtener la lista de viajes finalizados para un cliente específico.
     *
     * @param cliente El cliente para el que se buscan los viajes finalizados.
     * @return La lista de viajes finalizados del cliente.
     */
    public List<IViaje> getViajesFinalizadosCliente(Cliente cliente){
        return empresa.getViajesClienteFinalizados(cliente);

    }

}
