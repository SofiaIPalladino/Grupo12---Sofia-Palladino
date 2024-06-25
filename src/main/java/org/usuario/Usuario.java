package org.usuario;

import java.io.Serializable;

/**
 * Clase abstracta que representa a un usuario genérico en el sistema.
 * Esta clase define los atributos básicos y métodos comunes para todos los usuarios.
 */
public abstract class Usuario implements Serializable,Comparable<Usuario> {
	private String usuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private int cantidadViajes = 0;

	/**
	 * Constructor que inicializa todos los atributos de un usuario.
	 *
	 * @param usuario    Nombre de usuario.
	 * @param contrasenia Contraseña del usuario.
	 * @param nombre     Nombre del usuario.
	 * @param apellido   Apellido del usuario.
	 */
	public Usuario(String usuario, String contrasenia, String nombre, String apellido) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	/**
	 * Constructor vacío por defecto necesario para la serialización.
	 */
	public Usuario() {
	}

	/**
	 * Retorna la cantidad de viajes realizados por el usuario.
	 *
	 * @return Cantidad de viajes realizados.
	 */
	public int getCantidadViajes() {
		return cantidadViajes;
	}

	/**
	 * Incrementa en uno la cantidad de viajes realizados por el usuario.
	 */
	public void sumarViaje() {
		this.cantidadViajes += 1;
	}

	/**
	 * Retorna el nombre de usuario.
	 *
	 * @return Nombre de usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Establece el nombre de usuario.
	 *
	 * @param usuario Nombre de usuario a establecer.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Retorna la contraseña del usuario.
	 *
	 * @return Contraseña del usuario.
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param contrasenia Contraseña a establecer.
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Retorna el nombre del usuario.
	 *
	 * @return Nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 *
	 * @param nombre Nombre a establecer.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el apellido del usuario.
	 *
	 * @return Apellido del usuario.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del usuario.
	 *
	 * @param apellido Apellido a establecer.
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public int compareTo(Usuario otroUsuario) {
		return this.usuario.compareTo(otroUsuario.usuario);
	}
}
