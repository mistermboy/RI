package uo.ri.ui.foreman.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class ListClientsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de Clientes\n");

		List<Map<String, Object>> map = ServicesFactory.getForemanService().findAllClients();
		for (Map<String, Object> m : map) {
			for (Map.Entry<String, Object> entry : m.entrySet()) {
				Console.print(entry.getKey()+"\t" + entry.getValue()+"\n");
			}
			Console.println();
		}

	}

}
