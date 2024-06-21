package org.usuario;

import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.viaje.IViaje;

import java.util.List;

public class Cliente extends Usuario{
    
    public Cliente(String usuario, String contrasenia, String nombre, String apellido) {
		super(usuario,contrasenia,nombre,apellido);
	}
    
	public void pagoViaje(IViaje viaje) throws ViajeNoEncontradoException {
		synchronized (viaje){
			viaje.pagarViaje();
			Empresa e= Empresa.getInstance();
			e.sumaRecaudado(viaje.getCosto());
		}
		System.out.println("Viaje pagado con exito");
	}

}
