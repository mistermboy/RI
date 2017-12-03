package uo.ri.ui.admin.action;

import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class GenerateBonosAction implements Action {

	@Override
	public void execute() throws BusinessException {

		ServicesFactory.getAdminService().generateBonos();
		
		System.out.println("Bonos generados correctamente");

	}

}
