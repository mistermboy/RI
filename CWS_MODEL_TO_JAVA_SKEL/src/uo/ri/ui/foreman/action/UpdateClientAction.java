package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class UpdateClientAction implements Action {

	@Override
	public void execute() throws BusinessException {

		ForemanService fS = Factory.service.forForeman();

		Long idClient = Console.readLong("Id del cliente");
		ClientDto c = fS.findClientById(idClient);

		if (c == null) {
			throw new BusinessException("No existe el cliente");
		}

		c.name = Console.readString("Nombre");
		c.surname = Console.readString("Apellidos");
		c.addressStreet = Console.readString("Calle");
		c.addressCity = Console.readString("Ciudad");
		c.addressZipcode = Console.readString("Codigo Postal");
		c.phone = Console.readString("Telefono");
		c.email = Console.readString("Correo");

		fS.updateClient(c);

		Console.println("Mecánico actualizado");

	}
}
