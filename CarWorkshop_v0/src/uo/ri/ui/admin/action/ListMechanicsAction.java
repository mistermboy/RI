package uo.ri.ui.admin.action;

import uo.ri.business.admin.FindAllMechanics;
import uo.ri.common.BusinessException;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");

		List<Map<String,Object>> map = new FindAllMechanics().execute();
		for(Map<String,Object> m:map) {
			for(Map.Entry<String,Object> entry:m.entrySet()) {
				Console.print(entry.getKey() + entry.getValue());
			}
		}
		
		

	}
}
