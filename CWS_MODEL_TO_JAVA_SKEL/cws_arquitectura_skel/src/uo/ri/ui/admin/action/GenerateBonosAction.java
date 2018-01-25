package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class GenerateBonosAction implements Action {

	@Override
	public void execute() throws BusinessException {

		AdminService as = Factory.service.forAdmin();
		as.generateVouchers();

		Console.println("Se han generado los bonos por 3 averias");
	}
}
