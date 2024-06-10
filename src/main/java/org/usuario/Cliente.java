package org.usuario;

import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.viaje.IViaje;

import java.util.List;

public class Cliente extends Usuario{
    
    public Cliente(Usuario usuario) {
		super(usuario.getUsuario(), usuario.getContrasenia(), usuario.getNombre(), usuario.getApellido());
	}

	public void pagoViaje(IViaje viaje) throws ViajeNoEncontradoException {
		viaje.setStatus("Pagado");
		Empresa e=Empresa.getInstance();
		e.sumaRecaudado(viaje.getCosto());
		System.out.println("Viaje pagado con exito");
	}


}
