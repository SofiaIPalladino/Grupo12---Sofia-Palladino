
package org.usuario;
public class UsuarioFactory {
	public Usuario crea(Usuario usuario) {
		if (usuario.getClass().equals(Administrador.class))
			return new Administrador(usuario);
		else
			return new Cliente(usuario);
	}
}
