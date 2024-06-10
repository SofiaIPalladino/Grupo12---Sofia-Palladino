package org.usuario;


public class Administrador extends Usuario{

	public Administrador(Usuario usuario) {
		super(usuario.getUsuario(), usuario.getContrasenia(), usuario.getNombre(), usuario.getApellido());
		// TODO Auto-generated constructor stub
	}



}
