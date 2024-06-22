package org.hilos;

import org.excepciones.NoChoferException;
import org.excepciones.NoVehiculoException;
import org.excepciones.ViajeNoEncontradoException;
import org.pedido.GestionPedidos;
import org.pedido.Pedido;
import org.sistema.Empresa;
import org.usuario.Cliente;
import org.viaje.GestionViajes;
import org.viaje.IViaje;


public class HiloCliente implements Runnable {
    private final int cantViajes;
    private Cliente cliente;
    private GestionViajes gestionViajes = new GestionViajes();
    private GestionPedidos gestionPedido= new GestionPedidos();

    public HiloCliente(Cliente cliente, int cantViajes) {
        this.cliente = cliente;
        this.cantViajes = cantViajes;
    }

    @Override
    public void run() {
        int contador = 0;
        IViaje viaje = null;
        Pedido pedido = null;
        Empresa empresa = Empresa.getInstance();
        boolean ejecutar = true;
        try {
            empresa.agregarInformacionAccionarHilos("El cliente " + this.cliente.getUsuario() + " se logueó");
            while (ejecutar && contador < this.cantViajes) {
                pedido = new Pedido("Zona Peligrosa", true, "usoBaul", 3, this.cliente, 20);
                contador++;
                try {
                    gestionPedido.evaluarPedido(pedido);
                    synchronized (empresa.getViajes()) {
                        viaje = gestionViajes.convertirPedidoEnViaje(pedido);

                    }
                    synchronized (empresa.getViajes()) {
                        while (!viaje.getStatus().equals("Iniciado")) {
                            empresa.getViajes().wait();
                        }
                        empresa.agregarInformacionAccionarHilos("El viaje del cliente " + this.cliente.getUsuario() + " ha iniciado");
                    }
                    synchronized (empresa.getViajes()) {
                        gestionViajes.pagarViaje(viaje);
                        empresa.agregarInformacionAccionarHilos("El cliente " + this.cliente.getUsuario() + " pagó el viaje");
                        viaje.notifyAll();
                    }
                } catch (NoVehiculoException e) {
                    empresa.agregarInformacionAccionarHilos("No hay vehiculo disponible para el pedido del cliente " + this.cliente.getUsuario());
                } catch (NoChoferException e) {
                    empresa.agregarInformacionAccionarHilos("No hay ningun chofer en la empresa");
                } catch (ViajeNoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
