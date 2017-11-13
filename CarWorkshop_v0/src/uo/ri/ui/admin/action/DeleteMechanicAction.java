package uo.ri.ui.admin.action;

import uo.ri.business.admin.DeleteMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico");

		new DeleteMechanic(idMecanico).execute();

		Console.println("Se ha eliminado el mecánico");
	}

}
