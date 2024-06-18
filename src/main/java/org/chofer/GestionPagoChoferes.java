package org.chofer;



import org.sistema.Empresa;
import org.usuario.Administrador;
import org.usuario.Usuario;

import java.util.List;

public class GestionPagoChoferes {
	
	Empresa e=Empresa.getInstance();

	/**
	 * Calcula y muestra los pagos que se deben realizar a los choferes.
	 * Solo los usuarios de tipo Administrador tienen permisos para realizar esta acción.
	 *
	 * @param usuario El usuario que intenta realizar el cálculo de pagos.
	 */

	public void calculoPagoChoferes(Usuario usuario) {
		if (usuario.getClass().equals(Administrador.class)) {  //comprueba que el usuario sea un administrador
			double sueldo=0;
			double totalsueldos=0;
			Chofer choferaux=null;
			
			List<Chofer> choferes=e.getChoferes();
			for(int i=0;i<choferes.size();i++) {
				choferaux=choferes.get(i);
				sueldo=choferaux.getSueldo();
				totalsueldos+=sueldo;
				System.out.println("El chofer: "+choferaux.getNombre()+" tiene que cobrar $"+sueldo);
			}
			System.out.println("Total de dinero a pagar por los sueldos: "+totalsueldos);
		}else {
			System.out.println("Solo el administrador puede pagar los salarios");	
		}
		
	}

}
