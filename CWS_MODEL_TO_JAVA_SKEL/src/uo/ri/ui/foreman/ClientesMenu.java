package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import uo.ri.ui.foreman.action.AddClientAction;
import uo.ri.ui.foreman.action.DeleteClientAction;
import uo.ri.ui.foreman.action.ListClientsAction;
import uo.ri.ui.foreman.action.ShowClientDetailsAction;
import uo.ri.ui.foreman.action.UpdateClientAction;

public class ClientesMenu extends BaseMenu {

	public ClientesMenu() {
		menuOptions = new Object[][] { { "Jefe de Taller > Gestión de Clientes", null },

				{ "Añadir cliente", AddClientAction.class }, { "Eliminar cliente", DeleteClientAction.class },
				{ "Modificar datos de cliente", UpdateClientAction.class },
				{ "Listar clientes", ListClientsAction.class },
				{ "Mostrar detalles de un cliente", ShowClientDetailsAction.class }};
	}

}
// , { "Modificar datos de cliente", UpdateClientAction.class },
// { "Eliminar cliente", DeleteClientAction.class },
// { "Mostrar detalles de un cliente", ShowClientDetailAction.class },
// { "Listar clientes", ListClientsAction.class },
// { "Listar clientes recomendados por otro cliente",
// ListClientsByRecomendatorAction.class },