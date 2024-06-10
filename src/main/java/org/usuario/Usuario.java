package org.usuario;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String usuario;
	private String contrasenia;
    private String nombre;
    private String apellido;
    private boolean admin;
	private int cantidadViajes = 0;
    
	public Usuario(String usuario, String contrasenia, String nombre, String apellido) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Usuario() {
	}

	public int getCantidadViajes() {
		return cantidadViajes;
	}

	public void sumarViaje() {
		this.cantidadViajes+=1;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
    
    

}
