package org.puntajes;


import org.chofer.Chofer;
import org.sistema.Empresa;
import org.usuario.Administrador;
import org.usuario.Usuario;

import java.util.List;

/**
 * Clase que se encarga de gestionar los puntajes de los choferes
 */

//clase no utilizada en esta segunda entrega pero la dejo igualmente porque es necesaria para cumplir con lo pedido en la primer entrega

public class GestionPuntajes {

	/**
	 * Metodo que se encarga de actualizar los puntajes de los choferes.
	 *
	 * @param usuario El usuario que intenta actualizar los puntajes
	 * siendo solo un administrador capaz de actualizar los puntajes.
	 *
	 */

    public void actualizarPuntajes(Usuario usuario) {
    	if (usuario.getClass().equals(Administrador.class)) {
    		Chofer chofermax=null;
    		double maxDistancia = 0;
    		Empresa empresa=Empresa.getInstance();
    		List<Chofer> choferes=empresa.getChoferes();
    		for (int i = 0; i <  choferes.size(); i++) {
    			Chofer c=choferes.get(i);
    			c.agregaPuntaje(c.cantViajes()*5);
    			if (c.getKm() > maxDistancia) {
    				maxDistancia = c.getKm();
    				chofermax=c;
                
    			}
    		}
    		if (chofermax!=null)
    			chofermax.agregaPuntaje(15);
    	}else 
    		System.out.println("Solo el administrador puede actualizar los puntajes");	  	
    }

	/**
	 * Muestra los puntajes de los choferes.
	 *
	 * @param usuario El usuario que intenta mostrar los puntajes.
	 */

    public void MuestraPuntajes(Usuario usuario) {
    	if (usuario.getClass().equals(Administrador.class)) {
    		Empresa empresa=Empresa.getInstance();
    		List<Chofer> choferes=empresa.getChoferes();
    		System.out.println("Listado de puntajes: ");
    		for (int i=0;i<choferes.size();i++) {
    			Chofer c=choferes.get(i);
    			System.out.println(c.getNombre()+" con DNI "+c.getDni()+": "+c.getPuntaje());
    		}
    		System.out.println("--------------------------------------");
    	}else
    		System.out.println("Solo el administrador puede actualizar los puntajes");	 	
    }
    
}

