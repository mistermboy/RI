package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class DeleteClientAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		Long idClient = Console.readLong("Id del cliente");

		ServicesFactory.getForemanService().deleteClient(idClient);

		Console.println("Se ha eliminado el cliente");
	}
}
