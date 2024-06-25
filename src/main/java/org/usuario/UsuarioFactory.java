package org.usuario;

/**
 * La clase UsuarioFactory es una fábrica que permite crear instancias de objetos Usuario
 * basándose en un parámetro booleano que indica si el usuario a crear es un administrador o un cliente.
 */
public class UsuarioFactory {

	/**
	 * Crea y devuelve una instancia de Usuario (Administrador o Cliente) según el parámetro admin.
	 *
	 * @param usuario    El nombre de usuario del nuevo usuario.
	 * @param contrasenia La contraseña del nuevo usuario.
	 * @param nombre     El nombre del nuevo usuario.
	 * @param apellido   El apellido del nuevo usuario.
	 * @param admin      Indica si el usuario a crear es un administrador (true) o un cliente (false).
	 * @return Una instancia de Usuario, que puede ser Administrador o Cliente.
	 */
	public static Usuario crea(String usuario, String contrasenia, String nombre, String apellido, boolean admin) {
		if (admin) {
			return new Administrador(usuario, contrasenia, nombre, apellido);
		} else {
			return new Cliente(usuario, contrasenia, nombre, apellido);
		}
	}
}