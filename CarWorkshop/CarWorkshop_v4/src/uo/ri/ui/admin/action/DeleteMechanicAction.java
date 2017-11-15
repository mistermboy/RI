package uo.ri.ui.admin.action;

import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico");

		AdminServiceImpl admin = new AdminServiceImpl();
		admin.deleteMechanic(idMecanico);

		Console.println("Se ha eliminado el mecánico");
	}

}
