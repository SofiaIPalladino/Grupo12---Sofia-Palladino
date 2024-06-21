package org.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import org.excepciones.UsuarioExistenteException;
import org.excepciones.ViajeNoEncontradoException;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.usuario.GestionUsuario;
import org.usuario.Usuario;
import org.viaje.IViaje;
import org.vista.VentanaPedido;
import org.vista.VentanaViajeActual;

public class ControladorUsuario implements ActionListener,Observer{
	private Empresa empresa;
    private IViaje viaje;
    private GestionUsuario gestionUsuario;
    private VentanaViajeActual ventana;	
    private Cliente cliente;
    
    public ControladorUsuario(IViaje viaje,Cliente cliente) {
        this.empresa = Empresa.getInstance();
        this.viaje = viaje;
        this.cliente=cliente;
        this.ventana = new VentanaViajeActual(viaje,this);
        this.ventana.setVisible(true);
         empresa.addObserver(this);
        
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
	
    public void registrarUsuario(String usuario, String nombre, String apellido, String contrasenia) throws UsuarioExistenteException {
        try {
            gestionUsuario.existeUsuario(usuario,true);
            gestionUsuario.agregaUsuario(usuario, contrasenia, nombre, apellido);
        } catch (UsuarioExistenteException ex) {
            // TODO Auto-generated catch block
            throw (ex);
        }
    }
    
    public void pagarViaje() throws ViajeNoEncontradoException {
        this.cliente.pagoViaje(viaje);;
    }
    
    public void accionCerrar(){
        ventana.dispose();
        new VentanaPedido(viaje.getCliente()).setVisible(true);
    }

    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof IViaje) {
            IViaje viaje = (IViaje) arg;
            if (viaje.equals(this.viaje)) {
                ventana.actualizarEstado(viaje);
            }
        }
    }
    

}
