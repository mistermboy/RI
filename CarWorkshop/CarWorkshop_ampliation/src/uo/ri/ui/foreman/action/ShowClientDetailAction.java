package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class ShowClientDetailAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long idClient = Console.readLong("Id del cliente");

		System.out.println(ServicesFactory.getForemanService().showDetailClient(idClient));

	}

}
