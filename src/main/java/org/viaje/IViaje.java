package org.viaje;


import org.chofer.Chofer;
import org.pedido.Pedido;
import org.usuario.Cliente;
import org.vehiculo.Vehiculo;

import java.util.Date;
import java.util.Observable;

/**
 * Interfaz que modela una parte del comportamiento de los viajes.<br>
 */
public interface IViaje extends Comparable<IViaje>{

    Vehiculo getVehiculo();
    void setVehiculo(Vehiculo vehiculo);
	double getCosto();
	Pedido getPedido();
    double getDistanciaReal();
    void setStatus(String s);
    void setChofer(Chofer chofer);
    String getStatus();
    Chofer getChofer();
    Cliente getCliente();
    Date getFecha();
    String getZona();
    String getMascota();
    String getEquipaje();
    int getCantidadPersonas();
    void iniciarViaje(Chofer chofer);
    void asignarVehiculo(Vehiculo vehiculo);
    void pagarViaje();
    void finalizarViaje();
    public Object clone() throws CloneNotSupportedException;

    
}
