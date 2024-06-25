package org.sistema;
import org.usuario.Usuario;
import org.usuario.UsuarioFactory;

import java.util.ArrayList;
//no tiene uso, tengo que borrarlo
public class SistemaLogueo{

    private ArrayList<Usuario> usuarios;

    public SistemaLogueo() {
        this.usuarios = new ArrayList<>();
    }

    public boolean existeUsuario(String usuario) {
        boolean existe = false;
        int i = 0;
        while (i < usuarios.size() && !existe) {
            if (usuarios.get(i).getUsuario().equals(usuario)) {
               	existe = true;
            }
            i++;
        }
        return existe;
    }

    public void registrarUsuario(String usuario, String contrasenia, String nombre, String apellido) {
    	UsuarioFactory factoryUsuario = new UsuarioFactory();
    	Usuario nuevoUsuario = factoryUsuario.crea(usuario, contrasenia, nombre, apellido,false);
        usuarios.add(nuevoUsuario);
    }

    
    public boolean login(String usuario, String contrasenia) {
        boolean autenticado = false;
        int i = 0;
        while (i < usuarios.size() && !autenticado) {
            if (usuarios.get(i).getUsuario().equals(usuario) && usuarios.get(i).getContrasenia().equals(contrasenia)) {
                autenticado = true;
            }
            i++;
        }
        return autenticado; 
    }
}


