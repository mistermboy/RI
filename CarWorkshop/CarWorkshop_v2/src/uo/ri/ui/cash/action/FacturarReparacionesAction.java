package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.impl.CashServiceImpl;
import uo.ri.business.impl.cash.CreateInvoiceFor;
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
		
		CashServiceImpl c = new CashServiceImpl();
		Map<String, Object> factura = c.createInvoiceFor(idsAveria);
		
		mostrarFactura((long) factura.get("numeroFactura"),
				(Date) factura.get("fechaFactura"),
				(double) factura.get("totalFactura"),
				(double) factura.get("iva"),
				(double) factura.get("importe"));
		
	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}
	
	
	private void mostrarFactura(long numeroFactura, Date fechaFactura, double totalFactura, double iva,
			double totalConIva) {

		Console.printf("Factura nº: %d\n", numeroFactura);
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", fechaFactura);
		Console.printf("\tTotal: %.2f €\n", totalFactura);
		Console.printf("\tIva: %.1f %% \n", iva);
		Console.printf("\tTotal con IVA: %.2f €\n", totalConIva);
	}

}
