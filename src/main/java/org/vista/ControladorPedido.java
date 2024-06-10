package org.vista;

import org.chofer.Chofer;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.usuario.Usuario;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;

import java.util.ArrayList;
import java.util.List;

public class ControladorPedido {
    private Empresa empresa;
    private VentanaLogin vista;
    private Usuario model;

    public ControladorPedido() {
        this.empresa = Empresa.getInstance();
    }

    public void evaluaPedido(Pedido p) throws NoVehiculoException,NoChoferException {
        List<Vehiculo> vehiculos = empresa.getVehiculos();
        try {
            buscarVehiculo(vehiculos, p.getCantPersonas(), p.usoBaul(), p.getMascota());
            buscarChofer(empresa.getChoferes());
        } catch (NoVehiculoException | NoChoferException e) {
            throw (e);
        }
        empresa.agregaPedido(p);
    }

    public List<IViaje> getViajesFinalizadosCliente(Cliente cliente){
        return empresa.getViajesClienteFinalizados(cliente);

    }

    public boolean buscarVehiculo(List<Vehiculo> v, int cantPasajeros, boolean baul, boolean mascota ) throws NoVehiculoException {
        ArrayList<Vehiculo> vehiculosCumplen = new ArrayList<Vehiculo>();
        int i = 0;
        Vehiculo p=null;
        while (i < v.size()) {
            p= v.get(i);
            if (p.verificaPasajeros(cantPasajeros) && p.verificaBaul(baul)&& p.verificaMascota(mascota)) {
                return true;
            }
            i++;
        }
        throw new NoVehiculoException();
    }

    public boolean buscarChofer(List<Chofer> choferes) throws NoChoferException {
        for (Chofer c : choferes) {
            return true;
        }
        throw new NoChoferException("NO existe un chofer disponible");
    }
}
