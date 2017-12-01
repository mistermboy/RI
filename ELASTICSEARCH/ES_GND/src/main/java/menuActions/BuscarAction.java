package menuActions;

import AppKit.NSAction;
import UIKit.UIConsole;
import elasticsearch.ElasticSearch;
import elasticsearch.ElasticSearchQuery;

public class BuscarAction implements NSAction {
	
ElasticSearch es;
	
	public BuscarAction(ElasticSearch es) {
		this.es = es;
	}
	
	

	@Override
	public void execute() {
		UIConsole.print("Escriba la palabra que quiere buscar: ");
		String searchedWord = UIConsole.readLine();
		try {
			new ElasticSearchQuery(es.getClient())
			.parseResponse(new ElasticSearchQuery(es.getClient())
					.getRelatedTerms(searchedWord));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
