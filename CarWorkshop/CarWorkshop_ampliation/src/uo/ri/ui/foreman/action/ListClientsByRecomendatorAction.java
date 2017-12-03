package uo.ri.ui.foreman.action;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class ListClientsByRecomendatorAction implements Action {

	@Override
	public void execute() throws BusinessException {

		long idRecomendador = Console.readLong("Id del recomendador");
		
		Console.println("\nListado de Clientes Recomendados por el cliente con id "+ idRecomendador );

		List<Map<String, Object>> map = ServicesFactory.getForemanService().findAllClientsByRecomendator(idRecomendador);
		for (Map<String, Object> m : map) {
			for (Map.Entry<String, Object> entry : m.entrySet()) {
				Console.print(entry.getKey() + entry.getValue());
			}
		}

	}

}
