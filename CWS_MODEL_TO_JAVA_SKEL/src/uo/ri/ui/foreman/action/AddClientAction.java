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
		c.addressStreet = Console.readString("Calle");
		c.addressCity = Console.readString("Ciudad");
		c.addressZipcode = Console.readString("Codigo Postal");
		c.phone = Console.readString("Telefono");
		c.email = Console.readString("Correo");

		// String recomendacion = Console.readString("¿Viene usted recomendado por otro
		// cliente? [s|n]");

		ForemanService fs = Factory.service.forForeman();
		fs.addClient(c, null);

		// if (recomendacion.equals("s")) {
		// long id_recomendador = Console.readLong("Id del recomendador");
		// fs.addClient(c, id_recomendador);
		//
		// } else if (recomendacion.equals("n")) {
		// fs.addClient(c, null);
		// }

		Console.println("Nuevo cliente añadido");

	}

}
