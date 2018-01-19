package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.business.impl.BusinessServiceFactory;
import uo.ri.conf.Factory;
import uo.ri.persistence.jpa.JpaRepositoryFactory;
import uo.ri.persistence.jpa.executor.JpaExecutorFactory;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller", null },
			{ "Recepción en taller", RecepcionMenu.class }, 
			{ "Gestión de clientes", ClientesMenu.class },
			{ "Gestión de vehículos", VehiculosMenu.class },
			{ "Revisar historial de un cliente", NotYetImplementedAction.class }, 
		};
	}

	public static void main(String[] args) {
		new MainMenu().config().execute();
	}

	private MainMenu config() {
		Factory.executor = new JpaExecutorFactory();
        Factory.repository = new JpaRepositoryFactory();
        Factory.service = new BusinessServiceFactory();
        
		
		return this;
	}

}
