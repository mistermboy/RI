package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;
import uo.ri.common.BusinessException;

public class AddClientAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		String dni = Console.readString("Dni");
		int zipcode = Console.readInt("Codigo Postal");
		int telefono = Console.readInt("Telefono");
		String correo = Console.readString("Correo");

	//	String recomendacion = Console.readString("¿Viene usted recomendado por otro cliente?  [Si|No]");

//		if (recomendacion.equals("Si")) {
//			ServicesFactory.getForemanService().addClient(dni, nombre, apellidos, zipcode, telefono, correo);
//		} else if (recomendacion.equals("No")) {
//			ServicesFactory.getForemanService().addClient(dni, nombre, apellidos, zipcode, telefono, correo);
//		}

		ServicesFactory.getForemanService().addClient(dni, nombre, apellidos, zipcode, telefono, correo);
		
		// Mostrar resultado
		Console.println("Nuevo cliente añadido");

	}

}
