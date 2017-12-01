package core;

import UIKit.UIConsole;
import elasticsearch.ElasticSearch;
import elasticsearch.ElasticSearchConnection;
import elasticsearch.ElasticSearchQuery;

public class Main {

	private static ElasticSearch ES = new ElasticSearchConnection(
			"elasticsearch", 9300);

	public static void main(String[] args) throws Exception {

		// Mensaje de bienvenida.
		UIConsole.print("WELCOME TO RELATED TERMS SEARCH ENGINE!!! \n");

		// Realiza la conexión ÚNICA a elasticSearch.
		ES.connect();

		// Pregunto si deseo indexar por si ya está indexado.
		UIConsole.print("Desea indexar la colección? (S/N): ");
		String ans = UIConsole.readLine();
		if (ans.equals("S")) {

			UIConsole.print("indexing...\n");

			ES.indexDocument();

			UIConsole.print("done...\n");

		}
		UIConsole.print("> Indexado saltado\n");

		// Se le pide al usuario la palabra de la cual quiere buscar términos
		// relacionados.
		String cont = "S";
		while (cont.equals("S")) {

			UIConsole.print("Escriba la palabra que quiere buscar: ");
			String searchedWord = UIConsole.readLine();

			UIConsole.print("Bucando: " + searchedWord + "\n");
			UIConsole.print("Searching for related terms...\n");

			new ElasticSearchQuery(ES.getClient())
					.parseResponse(new ElasticSearchQuery(ES.getClient())
							.getRelatedTerms(searchedWord));

			UIConsole.print("Terminado...\n");
			UIConsole.print("Quiere continuar buscando palabras? (S/N): ");
			cont = UIConsole.readLine();
		}
		UIConsole.print("GRACIAS POR USAR RELATED TERMS SEARCH ENGINE\n");
		ES.disconnect();
	}

}