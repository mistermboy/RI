package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.cash.CreateInvoiceFor;
import uo.ri.common.BusinessException;

public class FacturarReparacionesAction implements Action {

	
	public FacturarReparacionesAction() {
		
	}
	
	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();
		
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while (masAverias());
		
		CreateInvoiceFor c = new CreateInvoiceFor(idsAveria);
		
		Map<String, Object> factura = c.execute();
		
	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}
