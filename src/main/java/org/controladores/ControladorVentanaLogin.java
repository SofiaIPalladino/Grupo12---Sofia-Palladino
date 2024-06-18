package org.controladores;

import org.excepciones.UsuarioExistenteException;
import org.sistema.Empresa;
import org.usuario.Usuario;
import org.vista.VentanaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controlador para la ventana de login.
 * Maneja la validación de usuarios y contraseñas, y la interacción con la vista de login.
 */
public class ControladorVentanaLogin implements ActionListener {
    private Empresa empresa;
    private VentanaLogin vista;
    private Usuario model;

    public ControladorVentanaLogin() {
        this.empresa = Empresa.getInstance();
    }

    /**
     * Metodo que se encarga de validar si un usuario existe en la empresa.
     *
     * @param usuario El nombre de usuario a validar.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean validaUsuario(String usuario) {
        try {
            return empresa.existeUsuario(usuario, false);
        }catch(UsuarioExistenteException e) {}
        return false;
    }

    /**
     * Valida la contraseña de un usuario.
     *
     * @param usuario El nombre de usuario.
     * @param contrasenia La contraseña del usuario.
     * @return El usuario si la contraseña es correcta, null en caso contrario.
     */
    public Usuario validaContrasenia(String usuario,String contrasenia) {
        return empresa.validaContrasenia(usuario, contrasenia);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

}