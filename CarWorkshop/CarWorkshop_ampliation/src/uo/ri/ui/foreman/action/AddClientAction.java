package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;
import uo.ri.common.BusinessException;

public class AddClientAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		String dni = Console.readString("Dni");
		int zipcode = Console.readInt("Codigo Postal(número)");
		int telefono = Console.readInt("Telefono(número)");
		String correo = Console.readString("Correo");

		String recomendacion = Console.readString("¿Viene usted recomendado por otro cliente?  [s|n]");

		if (recomendacion.equals("s")) {
			long idRecomendador = Console.readLong("Id del recomendador");
			ServicesFactory.getForemanService().addClientWithRecomendator(dni, nombre, apellidos, zipcode, telefono,
					correo, idRecomendador);
		} else if (recomendacion.equals("n")) {
			ServicesFactory.getForemanService().addClient(dni, nombre, apellidos, zipcode, telefono, correo);
		}

		Console.println("Nuevo cliente añadido");

	}

}
