package org.controladores;

import org.excepciones.UsuarioExistenteException;
import org.sistema.Empresa;

public class ControladorVentanaRegistro {
    private Empresa empresa;

    public ControladorVentanaRegistro() {
        this.empresa = Empresa.getInstance();
    }

    /**
     * Método que se encarga de registrar un nuevo usuario en la empresa.
     *
     * @param usuario El nombre de usuario.
     * @param nombre El nombre del usuario.
     * @param apellido El apellido del usuario.
     * @param contrasenia La contraseña del usuario.
     * @throws UsuarioExistenteException Si el usuario ya existe en la empresa.
     */
    public void registrarUsuario(String usuario, String nombre, String apellido, String contrasenia) throws UsuarioExistenteException {
        try {
            empresa.existeUsuario(usuario,true);
            empresa.agregaCliente(usuario, contrasenia, nombre, apellido);
        } catch (UsuarioExistenteException ex) {
            // TODO Auto-generated catch block
            throw (ex);
        }
    }
}


