package menuActions;

import AppKit.NSAction;
import UIKit.UIConsole;
import elasticsearch.ElasticSearch;

public class SalirAction implements NSAction {

	ElasticSearch es;
	
	public SalirAction(ElasticSearch es) {
		this.es = es;
	}

	@Override
	public void execute() {
		UIConsole.print("GRACIAS POR USAR RELATED TERMS SEARCH ENGINE\n");
		es.getClient().close();
		System.exit(1);
	}

}
