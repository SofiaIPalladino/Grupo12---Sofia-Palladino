package org.pedido;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.sistema.Empresa;
import org.vehiculo.Vehiculo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GestionPedidos implements Serializable {
	private List<Pedido> pedidos;  // Lista que almacena todos los pedidos gestionados.

	/**
	 * Constructor de la clase GestionPedidos.
	 * Inicializa la lista de pedidos como una lista vacía.
	 */
	public GestionPedidos() {
		this.pedidos = new ArrayList<>();
	}

	/**
	 * Método para agregar un pedido a la lista de pedidos.
	 *
	 * @param pedido El pedido que se desea agregar.
	 */
	public void agregarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	/**
	 * Método sincronizado para obtener la lista de todos los pedidos.
	 *
	 * @return Lista de todos los pedidos gestionados.
	 */
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos){
		this.pedidos = pedidos;
	}

	/**
	 * Método privado para buscar un vehículo que cumpla con los requisitos del pedido.
	 *
	 * @param p El pedido para el cual se busca un vehículo.
	 * @return true si se encuentra un vehículo que cumple con los requisitos, false si no.
	 * @throws NoVehiculoException Si no se encuentra ningún vehículo disponible que cumpla con los requisitos.
	 */
	private boolean buscarVehiculo(Pedido p) throws NoVehiculoException {
		for (Vehiculo vehiculo : Empresa.getInstance().getVehiculos()) {
			if (vehiculo.verificaPasajeros(p.getCantPersonas()) &&
					vehiculo.verificaBaul(p.usoBaul()) &&
					vehiculo.verificaMascota(p.getMascota())) {
				return true;  // Se encontró un vehículo que cumple con los requisitos.
			}
		}
		return false;  // No se encontró ningún vehículo que cumpla con los requisitos.
	}

	/**
	 * Método privado para verificar si hay al menos un chofer trabajando.
	 *
	 * @return true si hay al menos un chofer trabajando, false si no hay ninguno.
	 */
	private boolean buscarChofer() {
		return !Empresa.getInstance().getChoferes().isEmpty();
	}

	/**
	 * Método para evaluar un pedido y verificar si hay vehículo y chofer disponibles para atenderlo.
	 *
	 * @param p El pedido que se desea evaluar.
	 * @throws NoVehiculoException Si no hay vehículos disponibles que cumplan con los requisitos del pedido.
	 * @throws NoChoferException   Si no hay choferes trabajando para asignar al pedido.
	 */
	public void evaluarPedido(Pedido p) throws NoVehiculoException, NoChoferException {
		if (!buscarVehiculo(p)) {
			throw new NoVehiculoException("No hay vehículos disponibles que cumplan con los requisitos del pedido.");
		}
		if (!buscarChofer()) {
			throw new NoChoferException("No hay choferes trabajando.");
		}
		agregarPedido(p);  // Agrega el pedido a la lista de pedidos si todo está disponible.
	}
}
