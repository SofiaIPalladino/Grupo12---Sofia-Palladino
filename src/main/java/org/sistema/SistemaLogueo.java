package org.sistema;
import org.usuario.Usuario;
import org.usuario.UsuarioFactory;

import java.util.ArrayList;

public class SistemaLogueo{

    private ArrayList<Usuario> usuarios;

    public SistemaLogueo() {
        this.usuarios = new ArrayList<>();
    }

    public boolean existeUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public void registrarUsuario(String usuario, String contrasenia, String nombre, String apellido) {
    	UsuarioFactory factoryUsuario = new UsuarioFactory();
    	Usuario nuevoUsuario = factoryUsuario.crea(usuario, contrasenia, nombre, apellido,false);
        usuarios.add(nuevoUsuario);
    }

    
    //corregir con un while para no po
    public boolean login(String usuario, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getContrasenia().equals(contrasenia)) {
                return true; // usuario y contraseña correctos
            }
        }
        return false; // usuario o contraseña incorrectos
    }
}


