package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class DeleteClientAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long idClient = Console.readLong("Id del cliente");

		ForemanService fs = Factory.service.forForeman();
		fs.deleteClient(idClient);

		Console.println("Se ha eliminado el cliente");

	}
}
