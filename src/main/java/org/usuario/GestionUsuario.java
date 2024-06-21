package org.usuario;

import org.excepciones.UsuarioExistenteException;
import org.excepciones.UsuarioInexistenteException;
import org.sistema.Empresa;

import java.util.Iterator;
import java.util.List;


/**
 * Esta clase se encarga de gestionar a los usuarios, permitiendo crearlos, modificarlos,
 * consultar la existencia de los mismos y mostrar el listado de viajes realizados por un cliente ingresado
 * 
 * 
 */

public class GestionUsuario{
	
	public Usuario creaUsuario(Usuario usuario) throws UsuarioExistenteException {
		  Iterator<Usuario> iterator =  Empresa.getInstance().getUsuarios().iterator();
	        while (iterator.hasNext()) {
	            Usuario usuarios1 = iterator.next();
	            if (usuarios1.getUsuario().equals(usuario.getUsuario())) {
	            	throw new UsuarioExistenteException("Usuario ya existente: "+usuario.getUsuario());
	            }
	        }
	       UsuarioFactory factoryUsuario = new UsuarioFactory();
	       return factoryUsuario.crea(usuario.getUsuario(),usuario.getContrasenia(),usuario.getNombre(),usuario.getApellido(),false); //creacion de cliente
	}
	
    public void agregaUsuario(String usuario, String contrasenia, String nombre, String apellido) {
        UsuarioFactory factoryUsuario = new UsuarioFactory();
    //    Usuario nuevoUsuario = new Usuario(usuario, contrasenia, nombre, apellido);
        Usuario usuarioFactory = factoryUsuario.crea(usuario, contrasenia, nombre, apellido,false);
        this.usuarios.add(usuarioFactory);
        if (!usuarioFactory.getClass().equals(Administrador.class))
            this.clientes.add((Cliente) usuarioFactory);
    }

    public boolean existeUsuario(String usuarioing, boolean registro) throws UsuarioExistenteException {
        Iterator<Usuario> iterator = this.getUsuarios().iterator();
        while (iterator.hasNext()) {
            Usuario usuarios1 = iterator.next();
            if (usuarios1.getUsuario().equals(usuarioing)) {
                if (registro)
                    throw new UsuarioExistenteException("Usuario ya existente: " + usuarioing);
                else
                    return true;
            }
        }
        return false;
    }

    public Usuario validaContrasenia(String usuarioing, String contrasenia) {
        Iterator<Usuario> iterator = this.getUsuarios().iterator();
        while (iterator.hasNext()) {
            Usuario usuarios1 = iterator.next();
            if (usuarios1.getUsuario().equals(usuarioing) && usuarios1.getContrasenia().equals(contrasenia)) {
                return usuarios1;
            }
        }
        return null;
    }
	
	public void modificaUsuario(String nombreUsuario, String contraseniaNueva, String nombre, String apellido) throws UsuarioInexistenteException {
		List<Usuario> listaUsuarios = Empresa.getInstance().getUsuarios();
		Usuario usuario = null;
		int i=0;
		while (i < listaUsuarios.size() && usuario == null) {
			Usuario usuarioAux = listaUsuarios.get(i);
			if (usuarioAux.getUsuario().equalsIgnoreCase(nombreUsuario)) {
				usuario = usuarioAux;
			}
		}
		if (usuario == null) {
			throw new UsuarioInexistenteException("El usuario: " + nombreUsuario + " no existe");
		}
		if(contraseniaNueva != null) {
			usuario.setContrasenia(contraseniaNueva);
		}
		if(nombre != null) {
			usuario.setNombre(nombre);
		}
		if(apellido != null) {
			usuario.setApellido(apellido);
		}
	}

}
