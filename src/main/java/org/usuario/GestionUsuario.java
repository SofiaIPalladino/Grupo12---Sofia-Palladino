package org.usuario;

import org.excepciones.UsuarioExistenteException;
import org.excepciones.UsuarioInexistenteException;
import org.sistema.Empresa;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * Esta clase se encarga de gestionar a los usuarios en el sistema.
 */
public class GestionUsuario implements Serializable {

	public GestionUsuario() {
	}

	/**
	 * Crea un nuevo usuario en el sistema.
	 *
	 * @param usuario Usuario a crear.
	 * @return El usuario creado.
	 * @throws UsuarioExistenteException Si el usuario ya existe en el sistema.
	 */
	public Usuario creaUsuario(Usuario usuario) throws UsuarioExistenteException {
		Iterator<Usuario> iterator = Empresa.getInstance().getUsuarios().iterator();
		while (iterator.hasNext()) {
			Usuario usuarioExistente = iterator.next();
			if (usuarioExistente.getUsuario().equals(usuario.getUsuario())) {
				throw new UsuarioExistenteException("Usuario ya existente: " + usuario.getUsuario());
			}
		}
		return UsuarioFactory.crea(usuario.getUsuario(), usuario.getContrasenia(), usuario.getNombre(), usuario.getApellido(), false);
	}

	/**
	 * Agrega un usuario al sistema.
	 *
	 * @param usuario     Nombre de usuario del nuevo usuario.
	 * @param contrasenia Contraseña del nuevo usuario.
	 * @param nombre      Nombre del nuevo usuario.
	 * @param apellido    Apellido del nuevo usuario.
	 */
	public void agregaUsuario(String usuario, String contrasenia, String nombre, String apellido) {
		Usuario nuevoUsuario = UsuarioFactory.crea(usuario, contrasenia, nombre, apellido, false);
		Empresa.getInstance().getUsuarios().add(nuevoUsuario);
	}

	/**
	 * Verifica si un usuario existe en el sistema.
	 *
	 * @param usuarioing Nombre de usuario a verificar.
	 * @param registro   Indica si el usuario está siendo registrado.
	 * @return true si el usuario existe, false en caso contrario.
	 * @throws UsuarioExistenteException Si el usuario ya existe y está siendo registrado.
	 */
	public boolean existeUsuario(String usuarioing, boolean registro) throws UsuarioExistenteException {
		Iterator<Usuario> iterator = Empresa.getInstance().getUsuarios().iterator();
		while (iterator.hasNext()) {
			Usuario usuarioExistente = iterator.next();
			if (usuarioExistente.getUsuario().equals(usuarioing)) {
				if (registro) {
					throw new UsuarioExistenteException("Usuario ya existente: " + usuarioing);
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Valida la contraseña de un usuario.
	 *
	 * @param usuarioing  Nombre de usuario del usuario.
	 * @param contrasenia Contraseña a validar.
	 * @return El usuario si la contraseña es válida, null si no lo es.
	 */
	public Usuario validaContrasenia(String usuarioing, String contrasenia) {
		Iterator<Usuario> iterator = Empresa.getInstance().getUsuarios().iterator();
		while (iterator.hasNext()) {
			Usuario usuario = iterator.next();
			if (usuario.getUsuario().equals(usuarioing) && usuario.getContrasenia().equals(contrasenia)) {
				return usuario;
			}
		}
		return null;
	}

	/**
	 * Modifica los datos de un usuario existente en la lista de usuarios de la empresa.
	 * Si el usuario no existe, lanza una UsuarioInexistenteException.
	 * @param nombreUsuario Nombre del usuario a modificar.
	 * @param contraseniaNueva Nueva contraseña del usuario (puede ser null si no se desea modificar).
	 * @param nombre Nuevo nombre del usuario (puede ser null si no se desea modificar).
	 * @param apellido Nuevo apellido del usuario (puede ser null si no se desea modificar).
	 * @throws UsuarioInexistenteException Si el usuario no existe en la lista de usuarios.
	 */
	public void modificaUsuario(String nombreUsuario, String contraseniaNueva, String nombre, String apellido) throws UsuarioInexistenteException {
		List<Usuario> listaUsuarios = Empresa.getInstance().getUsuarios();
		Usuario usuario = null;
		int i = 0;
		while (i < listaUsuarios.size() && usuario == null) {
			Usuario usuarioAux = listaUsuarios.get(i);
			// Comparar los nombres de usuario ignorando mayúsculas y minúsculas
			if (usuarioAux.getUsuario().equalsIgnoreCase(nombreUsuario)) {
				usuario = usuarioAux; // Encontrar el usuario
			}
			i++; // Incrementar el contador para avanzar en la lista
		}
		if (usuario == null) {
			throw new UsuarioInexistenteException("El usuario: " + nombreUsuario + " no existe");
		}
		if (contraseniaNueva != null) {
			usuario.setContrasenia(contraseniaNueva);
		}
		if (nombre != null) {
			usuario.setNombre(nombre);
		}
		if (apellido != null) {
			usuario.setApellido(apellido);
		}
	}
}
