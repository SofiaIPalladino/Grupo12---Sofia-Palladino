package org.pedido;


import org.chofer.Chofer;
import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;
import org.viaje.IViaje;

import java.util.ArrayList;
import java.util.List;

public class GestionPedidos {
	private List<Vehiculo> vehiculosCumplen;

	Empresa empresa= Empresa.getInstance();

    public GestionPedidos(List<Vehiculo> vehiculosCumplen) {
        this.vehiculosCumplen = vehiculosCumplen;
    }


    public void evaluaPedido(Pedido p)  {
		List<Vehiculo> vehiculos=empresa.getVehiculos();
		Vehiculo v=null;
		try {
			buscarVehiculo(vehiculos,p.getCantPersonas(),p.usoBaul(),p.getMascota());
		} catch (NoVehiculoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		empresa.agregaPedido(p);					
	}
	
	
	public ArrayList<Vehiculo> buscarVehiculo(List<Vehiculo> v, int cantPasajeros, boolean baul, boolean mascota ) throws NoVehiculoException {
		ArrayList<Vehiculo> vehiculosCumplen = new ArrayList<Vehiculo>();
		int i = 0;
        Vehiculo p=null;
        while (i < v.size()) {
        	p= v.get(i);
            if (p.verificaPasajeros(cantPasajeros) && p.verificaBaul(baul)&& p.verificaMascota(mascota)) {
                vehiculosCumplen.add(p);
            }
            i++;
        }
		return vehiculosCumplen;
    }
	//no se usa por ahora
	public static Chofer buscarChofer(List<Chofer> c) throws NoChoferException {
        int i = 0;
        Chofer p=null;
        while (i < c.size()) {
        	p = c.get(i);
        	return p;//para en un futuro tener que elegier un chofer por x caracteristica
            }
            i++;
        
            throw new NoChoferException("NO existe un chofer disponible");
    }
	

}