package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class ShowClientDetailsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long idClient = Console.readLong("Id del cliente");

		ForemanService fS = Factory.service.forForeman();

		ClientDto client = fS.findClientById(idClient);

		Console.println("\nDetalles del cliente:\n");
		Printer.printClientDetails(client);

	}

}
