package org.controladores;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.excepciones.UsuarioExistenteException;
import org.excepciones.ViajeNoEncontradoException;
import org.pedido.Pedido;
import org.persistencia.PersistirDatos;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.IViaje;
import org.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ControladorCliente implements ActionListener, Observer {
    private IViaje viaje;
    private List<IViaje> historicoViajes;
    private VentanaViajeActual ventanaViajeActual;
    private VentanaPedido ventanaPedido;
    private VentanaLogin ventanaLogin;
    private VentanaViajesFinalizados ventanaViajesFinalizados;
    private VentanaInicio ventanaInicio;
    private VentanaRegistro ventanaRegistro;
    private Cliente cliente;


    public ControladorCliente() {
        ventanaInicio = new VentanaInicio(this);
        ventanaLogin = new VentanaLogin(this);
        ventanaPedido = new VentanaPedido(this);
        ventanaRegistro = new VentanaRegistro(this);
        historicoViajes=new ArrayList<>();
        ventanaInicio.setVisible(true);
        Empresa.getInstance().addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        switch (comando) {
            case "LOGIN":
                ventanaInicio.setVisible(false);
                ventanaLogin.setVisible(true);
                break;
            case "REGISTRARSE":
                ventanaInicio.setVisible(false);
                ventanaRegistro.setVisible(true);
                break;
            case "INGRESAR":
                intentoLogin();
                this.historicoViajes = Empresa.getInstance().getViajesClienteFinalizados(cliente);
                break;
            case "Registrar":
                intentoRegistro();
                break;
            case "Hacer Pedido":
                crearPedido();
                break;
            case "CERRAR":
                ventanaViajeActual.dispose();
                ventanaPedido.setVisible(true);
                break;
            case "Ver Historial":
                //cargarHistoricoViajes();
                ventanaViajesFinalizados = new VentanaViajesFinalizados(this.historicoViajes);
                ventanaViajesFinalizados.setVisible(true);
                break;
            case "PAGAR":
                pagaViaje();
                ventanaViajeActual.setBotonVisible();
                break;
            case "LEVANTAR ARCHIVO":
                cargarPersistencia();
                break;
        }
    }

    private void cargarPersistencia() {
        PersistirDatos persistirDatos = new PersistirDatos();
        try {
            persistirDatos.levantoDatos();
        } catch (IOException | ClassNotFoundException e) {
            this.ventanaInicio.mostrarMensaje("Error al intentar cargar archivo persistencia");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof IViaje) {
            this.viaje = (IViaje) arg;
            System.out.println("Entro a update de controladorCliente "+viaje.getStatus());
            if (viaje.getStatus().equals("Finalizado")) {
                historicoViajes.add(viaje);
                //this.ventanaViajesFinalizados.actualizarViaje(this.historicoViajes);
            }
            this.ventanaViajeActual.actualizarViaje(viaje);
        }
    }

    private void intentoLogin() {
        String usuario = this.ventanaLogin.getUsuario();
        String contrasenia = this.ventanaLogin.getContrasenia();
        try {
            if (!Empresa.getInstance().existeUsuario(usuario, false)) {
                this.ventanaLogin.mostrarMensaje("Usuario no existente");
            } else {
                this.cliente = (Cliente) Empresa.getInstance().validaContrasenia(usuario, contrasenia);
                if (cliente == null) {
                    this.ventanaLogin.mostrarMensaje("Contrasenia incorrecta");
                } else {
                    this.ventanaLogin.setVisible(false);
                    this.ventanaPedido.setVisible(true);

                }
            }
        } catch (UsuarioExistenteException e) {
            throw new RuntimeException(e);
        }
    }

    private void intentoRegistro() {
        String usuario = this.ventanaRegistro.getUsuario();
        String nombre = this.ventanaRegistro.getNombre();
        String apellido = this.ventanaRegistro.getApellido();
        String contrasenia = this.ventanaRegistro.getContrasenia();
        try {
            if (Empresa.getInstance().existeUsuario(usuario, true)) {
                this.ventanaRegistro.mostrarMensaje("Usuario ya existente");
            } else {
                this.cliente = Empresa.getInstance().agregaCliente(usuario, contrasenia, nombre, apellido);
                this.ventanaRegistro.mostrarMensaje("Registro exitoso");
                this.ventanaRegistro.setVisible(false);
                this.ventanaInicio.setVisible(true);
            }
        } catch (UsuarioExistenteException e) {
            this.ventanaRegistro.mostrarMensaje("No se pudo registar");
        }
    }

    private void crearPedido() {
        String zona = this.ventanaPedido.getZona();
        boolean mascota = this.ventanaPedido.getMascota();
        boolean equipaje = this.ventanaPedido.getEquipaje();
        int cantPersonas = this.ventanaPedido.getCantPersonas();
        double distancia = this.ventanaPedido.getDistancia();

        if (cantPersonas <= 0 || cantPersonas > 10) {
            this.ventanaPedido.mostrarMensaje("No existen vehiculos para esa cantidad de personas");
        } else if (distancia < 0) {
            this.ventanaPedido.mostrarMensaje("La distancia no puede ser negativa");
        } else {
            Pedido pedido = new Pedido(zona, mascota, equipaje ? "Baul" : "No Baul", cantPersonas, cliente, distancia);
            try {
                synchronized (Empresa.getInstance().getGestionPedidos()) {
                    Empresa.getInstance().evaluarPedido(pedido);
                    this.viaje = Empresa.getInstance().convertirPedidoEnViaje(pedido);
                    Empresa.getInstance().getGestionViajes().agregarViaje(viaje);


                    this.ventanaPedido.setVisible(false);
                }
                ventanaViajeActual = new VentanaViajeActual(this);
                this.ventanaViajeActual.setVisible(true);
                this.ventanaViajeActual.actualizarViaje(viaje);
                //Empresa.getInstance().notificarCambios();

            } catch (NoVehiculoException e) {
                this.ventanaPedido.mostrarMensaje("No se encuentra un vehiculo que cumpla con las condiciones de tu pedido");
            } catch (NoChoferException e) {
                this.ventanaPedido.mostrarMensaje("No se encuentran choferes en la empresa");
            }
        }
    }

    private void pagaViaje() {
        synchronized (this.viaje) {
            if (this.viaje.getStatus().equals("Iniciado")) {
                try {
                    Empresa.getInstance().getGestionViajes().pagarViaje(this.viaje);
                    this.ventanaViajeActual.mostrarMensaje("Viaje pagado");

                } catch (ViajeNoEncontradoException e) {
                    this.ventanaViajeActual.mostrarMensaje("No se pudo pagar el viaje");
                }
            }

        }
    }

    private void cargarHistoricoViajes() {
        this.ventanaViajesFinalizados.actualizarViaje(this.historicoViajes);
    }

}


