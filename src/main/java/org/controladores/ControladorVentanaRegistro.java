package org.controladores;

import org.excepciones.UsuarioExistenteException;
import org.sistema.Empresa;

public class ControladorVentanaRegistro {
    private Empresa empresa;

    public ControladorVentanaRegistro() {
        this.empresa = Empresa.getInstance();
    }

    public void registrarUsuario(String usuario, String nombre, String apellido, String contrasenia) throws UsuarioExistenteException {
        try {
            empresa.existeUsuario(usuario,true);
            empresa.agregaUsuario(usuario, contrasenia, nombre, apellido);
        } catch (UsuarioExistenteException ex) {
            // TODO Auto-generated catch block
            throw (ex);
        }
    }
}


