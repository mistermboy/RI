package uo.ri.ui.admin.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");

		List<Map<String, Object>> map = ServicesFactory.getAdminService()
				.findAllMechanics();
		for (Map<String, Object> m : map) {
			for (Map.Entry<String, Object> entry : m.entrySet()) {
				Console.print(entry.getKey() + entry.getValue());
			}
		}

	}
}
