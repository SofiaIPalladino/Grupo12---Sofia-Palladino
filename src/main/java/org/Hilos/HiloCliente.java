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


public class HiloCliente extends Thread {
    private final int cantViajes;
    private Cliente cliente;

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
            //empresa.agregarInformacionAccionarHilos("El cliente " + this.cliente.getUsuario() + " se logueó");
            while (ejecutar && contador < this.cantViajes) {
                pedido = new Pedido("Zona Peligrosa", true, "usoBaul", 3, this.cliente, 20);
                contador++;
                try {
                    Empresa.getInstance().getGestionPedidos().evaluarPedido(pedido);
                        viaje = Empresa.getInstance().getGestionViajes().convertirPedidoEnViaje(pedido);
                        Empresa.getInstance().getGestionViajes().agregarViaje(viaje);
                    synchronized (viaje) {
                        while (!viaje.getStatus().equals("Iniciado")) {
                            viaje.wait();
                        }
                      //  empresa.agregarInformacionAccionarHilos("El viaje del cliente " + this.cliente.getUsuario() + " ha iniciado");
                    }
                    //synchronized (empresa.getViajes()) {
                        Empresa.getInstance().getGestionViajes().pagarViaje(viaje);
                     //   empresa.agregarInformacionAccionarHilos("El cliente " + this.cliente.getUsuario() + " pagó el viaje");
                       // viaje.notifyAll();
                  //  }
                } catch (NoVehiculoException e) {
                 //   empresa.agregarInformacionAccionarHilos("No hay vehiculo disponible para el pedido del cliente " + this.cliente.getUsuario());
                } catch (NoChoferException e) {
                  //  empresa.agregarInformacionAccionarHilos("No hay ningun chofer en la empresa");
                } catch (ViajeNoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(empresa.getInformacionAccionarHilos());

    }

}
