package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class UpdateClientAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long idClient = Console.readLong("Id del cliente");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		String dni = Console.readString("Dni");
		int zipcode = Console.readInt("Codigo Postal(número)");
		int telefono = Console.readInt("Telefono(número)");
		String correo = Console.readString("Correo");

		ServicesFactory.getForemanService().updateClient(idClient, nombre, apellidos, dni, zipcode, telefono, correo);

		Console.println("Cliente actualizado");
	}

}
