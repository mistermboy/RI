package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class AddClientAction implements Action {

	@Override
	public void execute() throws BusinessException {

		
		ClientDto c = new ClientDto();
		c.name = Console.readString("Nombre");
		c.surname = Console.readString("Apellidos");
		c.dni = Console.readString("Dni");
		c.addressZipcode = Console.readString("Codigo Postal(número)");
		c.phone = Console.readString("Telefono(número)");
		c.email = Console.readString("Correo");

		//String recomendacion = Console.readString("¿Viene usted recomendado por otro cliente?  [s|n]");

//		if (recomendacion.equals("s")) {
//			long idRecomendador = Console.readLong("Id del recomendador");
//			ServicesFactory.getForemanService().addClientWithRecomendator(dni, nombre, apellidos, zipcode, telefono,
//					correo, idRecomendador);
//		} else if (recomendacion.equals("n")) {
//			ServicesFactory.getForemanService().addClient(dni, nombre, apellidos, zipcode, telefono, correo);
//		}
		
		ForemanService as = Factory.service.forForeman();
		as.addClient(c, null);

		Console.println("Nuevo cliente añadido");

	}

}
