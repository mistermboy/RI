package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class ClientesMenu extends BaseMenu {

	public ClientesMenu() {
		menuOptions = new Object[][] { { "Jefe de Taller > Gestión de Clientes", null },

				{ "Añadir cliente", AddClientAction.class }, { "Modificar datos de cliente", UpdateClientAction.class },
				{ "Eliminar cliente", DeleteClientAction.class },
				{ "Mostrar detalles de un cliente", ShowClientDetailAction.class },
				{ "Listar clientes", ListClientsAction.class },
				{ "Listar clientes recomendados por otro cliente", ListClientsByRecomendatorAction.class }, };
	}

}
