package org.usuario;


import org.excepciones.UsuarioExistenteException;
import org.excepciones.UsuarioInexistenteException;
import org.sistema.Empresa;
import org.viaje.IViaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Esta clase se encarga de gestionar a los usuarios, permitiendo crearlos, modificarlos,
 * consultar la existencia de los mismos y mostrar el listado de viajes realizados por un cliente ingresado
 *
 *
 */



public class GestionUsuario {
    Empresa empresa;


    public GestionUsuario() {
        this.empresa=Empresa.getInstance();
    }

    public void agregaUsuario(String usuario, String contrasenia, String nombre, String apellido,boolean admin) {
        UsuarioFactory factoryUsuario = new UsuarioFactory();
        Usuario usuarioFactory = factoryUsuario.crea(usuario, contrasenia, nombre, apellido,admin);
        this.empresa.agregaUsuario(usuarioFactory);
    }

    public void modificaUsuario(String nombreUsuario, String contraseniaNueva, String nombre, String apellido) throws UsuarioInexistenteException {
        List<Cliente> listaClientes = Empresa.getInstance().getClientes();
        Usuario usuario = null;
        int i=0;
        while (i < listaClientes.size() && usuario == null) {
            Usuario usuarioAux = listaClientes.get(i);
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

    public Cliente consultaUsuario(String nombreUsuario) {
        List<Cliente> listaClientes = Empresa.getInstance().getClientes();
        Cliente cliente=null;
        int i=0;
        while (i < listaClientes.size() && cliente==null) {
            Cliente c = listaClientes.get(i);
            if (c.getUsuario().equalsIgnoreCase(nombreUsuario)) {
                cliente=c;
            }
            i++;
        }
        return cliente;
    }


    public Usuario validaContrasenia(String usuarioing, String contrasenia) {
        Iterator<Cliente> iterator = this.empresa.getClientes().iterator();
        Cliente cliente = null;
        while (iterator.hasNext() && cliente==null) {
            Cliente c = iterator.next();
            if (c.getUsuario().equals(usuarioing) && c.getContrasenia().equals(contrasenia)) {
                cliente=c;
            }
        }
        return cliente;
    }


    public boolean existeUsuario(String usuarioing, boolean registro) throws UsuarioExistenteException {
        Iterator<Cliente> iterator = this.empresa.getClientes().iterator();
        Cliente cliente = null;
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

    public List<IViaje> getViajes(Cliente cliente){
        List<IViaje> listadoViajes = Empresa.getInstance().getViajes();
        List<IViaje> viajesUsuario = new ArrayList<IViaje>();
        for(IViaje viaje: listadoViajes) {
            if (viaje.getCliente() == cliente) {
                viajesUsuario.add(viaje);
            }
        }
        return viajesUsuario;
    }
}


   /* public Usuario creaUsuario(Usuario usuario) throws UsuarioExistenteException {
        Iterator<Usuario> iterator =  Empresa.getInstance().getUsuarios().iterator();
        while (iterator.hasNext()) {
            Usuario usuarios1 = iterator.next();
            if (usuarios1.getUsuario().equals(usuario.getUsuario())) {
                throw new UsuarioExistenteException("Usuario ya existente: "+usuario.getUsuario());
            }
        }
        UsuarioFactory factoryUsuario = new UsuarioFactory();
        return factoryUsuario.crea(usuario);
    }

    */
