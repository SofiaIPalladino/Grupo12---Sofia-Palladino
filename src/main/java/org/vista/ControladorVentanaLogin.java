package org.vista;

import org.excepciones.UsuarioExistenteException;
import org.sistema.Empresa;
import org.usuario.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ControladorVentanaLogin implements ActionListener {
    private Empresa empresa;
    private VentanaLogin vista;
    private Usuario model;

    public ControladorVentanaLogin() {
        this.empresa = Empresa.getInstance();
    }

    public boolean validaUsuario(String usuario) {
        try {
            return empresa.existeUsuario(usuario, false);
        }catch(UsuarioExistenteException e) {}
        return false;
    }

    public Usuario validaContrasenia(String usuario,String contrasenia) {
        return empresa.validaContrasenia(usuario, contrasenia);
    }

    //public void agregarUsuario(String usuario) {
      //  empresa.setUsuarioLogueado(usuario);
    //}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    //...
}