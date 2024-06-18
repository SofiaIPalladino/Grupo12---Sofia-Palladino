
package org.usuario;
public class UsuarioFactory {

	public Usuario crea(String usuario, String contrasenia, String nombre, String apellido,boolean admin) {
		if (admin)
			return new Administrador(usuario, contrasenia, nombre, apellido);
		else
			return new Cliente(usuario, contrasenia, nombre, apellido);
	}
}
