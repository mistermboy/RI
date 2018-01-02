package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		MechanicDto m = new MechanicDto();
		m.dni = Console.readString("Dni"); 
		m.name = Console.readString("Nombre"); 
		m.surname = Console.readString("Apellidos");
		
		// Procesar
		AdminService as = Factory.service.forAdmin();
		as.newMechanic( m );
				
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
