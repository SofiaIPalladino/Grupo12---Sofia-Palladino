package org.main;

public class main {
/*
	public static void main(String[] args) {
		
		Empresa e=Empresa.getInstance();
		GestionDeUsuarios gestion_usuarios =new GestionDeUsuarios();
		//GestionPedidos gestionPedidos=new GestionPedidos();
		GestionViajes gestionViajes=new GestionViajes();
		GestionPagoChoferes gestionPagoChoferes=new GestionPagoChoferes();
		GestionPuntajes gestionPuntajes=new GestionPuntajes();
		Usuario x=new Usuario("sofi", "1234","su casa", "2413256123", false);
		Usuario admi=new Usuario("sofi", "1234","su casa", "2413256123", true);
		Cliente cliente = null;
		try {
			cliente = (Cliente) gestion_usuarios.creaUsuario( x);
		} catch (UsuarioExistenteException ex) {
			// TODO Auto-generated catch block
			System.out.println("Usuario existente");
		}
		
		Pedido pedidomascota=new Pedido("Estandar",true,"UsoBaul",3,cliente,10);
		Pedido pedidobaul=new Pedido("Estandar",false,"UsoBaul",4,cliente,144);
		Pedido pedidocombi=new Pedido("Estandar",false,"UsoBaul",9,cliente,13);
		Pedido pedidoqueno=new Pedido("Estandar",true,"UsoBaul",9,cliente,13);	
		
		Automovil auto1=new Automovil("456624");
		Moto moto1=new Moto("32143");
		Combi combi1=new Combi("2325353");
		
		Chofer chofer1=new ChoferTemporario("451542","ChoferTemporario");
		Chofer chofer2=new ChoferContratado("451352","ChoferContratado");
		Chofer chofer3=new ChoferPermanente("453542","ChoferPermanente",4, Fecha.setFecha(1,4,2020));
		
		e.agregaVehiculo(combi1);
		e.agregaVehiculo(moto1);
		e.agregaVehiculo(auto1);
		e.agregaChofer(chofer1);
		e.agregaChofer(chofer2);
		e.agregaChofer(chofer3);
		
		gestionPedidos.evaluaPedido(pedidocombi);
		
		try {
			gestionViajes.CreoViaje();
		} catch (NoVehiculoException e1) {
			e1.printStackTrace();
		}
		
		try {
			gestionViajes.pagoViaje(cliente);
		} catch (ViajeNoEncontradoException e1) {
			e1.printStackTrace();
		}
		
		try {
			gestionViajes.finalizarViaje(chofer1);
		} catch (ViajeNoEncontradoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gestionPagoChoferes.calculoPagoChoferes(admi);
		gestionPuntajes.actualizarPuntajes(admi);
		gestionPuntajes.MuestraPuntajes(admi);
		IPersistencia persistencia = new PersistenciaXML();

		try {
			persistencia.abrirOutput("empresa.xml");

			EmpresaDTO edto= new EmpresaDTO(e);

			persistencia.escribir(edto);
			persistencia.cerrarOutput();

			persistencia.abrirInput("empresa.xml");
			EmpresaDTO leoEmpresa= (EmpresaDTO) persistencia.leer();
			e.setChoferes(edto.getChoferes());
			e.setVehiculos(edto.getVehiculos());
			System.out.println(leoEmpresa.getChoferes());

		}catch(Exception e3){}
	}
	
	*/

}
