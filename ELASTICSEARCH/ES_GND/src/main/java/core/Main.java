package core;

import org.apache.commons.lang3.math.NumberUtils;

import AppKit.NSMenu;
import AppKit.NSMenuItem;
import UIKit.UIConsole;
import elasticsearch.ElasticSearch;
import elasticsearch.ElasticSearchConnection;
import menuActions.BuscarAction;
import menuActions.IndexarAction;
import menuActions.SalirAction;

public class Main {

	private static ElasticSearch ES = new ElasticSearchConnection(
			"elasticsearch", 9300);

	public static void main(String[] args) throws Exception {
		
		NSMenu menu = new NSMenu();
		menu.add(new NSMenuItem("Indexar", new IndexarAction(ES)));
		menu.add(new NSMenuItem("Buscar términos relacionados", new BuscarAction(ES)));
		menu.add(new NSMenuItem("Salir", new SalirAction(ES)));

		// Mensaje de bienvenida.
		UIConsole.print("WELCOME TO RELATED TERMS SEARCH ENGINE!!! \n");

		// Realiza la conexión ÚNICA a elasticSearch.
		ES.connect();
		
		while(true) {
			menu.print();
			String option;
			do {
				System.out.print("Número de opción: ");
				option = UIConsole.readLine();
			} while(!NumberUtils.isDigits(option));
			menu.execute(Integer.parseInt(UIConsole.readLine()));
		}
	}
}