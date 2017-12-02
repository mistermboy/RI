package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class UpdateClientAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long idClient = Console.readLong("Id del cliente");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		ServicesFactory.getForemanService().updateClient(idClient, nombre, apellidos);

		// Mostrar resultado
		Console.println("Cliente actualizado");
	}

}
