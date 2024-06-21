package org.controladores;

import org.excepciones.UsuarioExistenteException;
import org.sistema.Empresa;
import org.usuario.GestionUsuario;
import org.usuario.Usuario;
import org.vista.VentanaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVentanaLogin implements ActionListener {
    private Empresa empresa;
    private GestionUsuario gestionUsuario;
    private VentanaLogin vista;
    private Usuario model;

    public ControladorVentanaLogin() {
        this.empresa = Empresa.getInstance();
    }

    public boolean validaUsuario(String usuario) {
        try {
            return gestionUsuario.existeUsuario(usuario, false);
        }catch(UsuarioExistenteException e) {}
        return false;
    }

    public Usuario validaContrasenia(String usuario, String contrasenia) {
        return gestionUsuario.validaContrasenia(usuario, contrasenia);
    }

    //public void agregarUsuario(String usuario) {
      //  empresa.setUsuarioLogueado(usuario);
    //}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //...
}