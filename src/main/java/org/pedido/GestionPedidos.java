package org.pedido;


import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.sistema.Empresa;
import org.vehiculo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class GestionPedidos {
	private Empresa empresa;
	private List<Pedido> pedidos;

	public GestionPedidos(Empresa empresa) {
		this.pedidos = new ArrayList<>();
		this.empresa = empresa;
	}

	public void agregarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public synchronized List<Pedido> getPedidos() {
		return pedidos;
	}

	private boolean buscarVehiculo(Pedido p) throws NoVehiculoException {
		for (Vehiculo vehiculo : empresa.getVehiculos()) {
			if (vehiculo.verificaPasajeros(p.getCantPersonas()) && vehiculo.verificaBaul(p.usoBaul()) && vehiculo.verificaMascota(p.getMascota())) {
				return true;
			}
		}
		return false;
	}

	private boolean buscarChofer(){
		if (!empresa.getChoferes().isEmpty())
			return true;
		else
			return false;
	}

	public void evaluarPedido(Pedido p) throws NoVehiculoException, NoChoferException {
		if (!buscarVehiculo(p)) {
			throw new NoVehiculoException("No hay vehículos disponibles que cumplan con los requisitos del pedido.");
		}
		if (!buscarChofer()) {
			throw new NoChoferException("No hay choferes trabajando.");
		}
		agregarPedido(p);
	}
}