package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.ui.admin.action.GenerateBonosAction;

public class BonosMenu extends BaseMenu{
	
	public BonosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de bonos", null},
			{"Generar bonos", GenerateBonosAction.class}
			
		};
	}

}
