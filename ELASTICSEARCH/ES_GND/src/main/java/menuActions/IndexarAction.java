package menuActions;

import AppKit.NSAction;
import UIKit.UIConsole;
import elasticsearch.ElasticSearch;

public class IndexarAction implements NSAction {
	
	private ElasticSearch es;
	private int documentsToIndex;
	private String filename;
	
	public IndexarAction(ElasticSearch es) {
		this.es = es;
	}

	@Override
	public void execute() {
		UIConsole.print("Inserte el número de docuemntos que quiere indexar: ");
		this.documentsToIndex = Integer.parseInt(UIConsole.readLine());
		UIConsole.print("Inserte el nombre del archivo junto a la extensión del archivo con los documentos: ");
		// 2008-Feb-02-04-EN.json
		this.filename = UIConsole.readLine();
		es.indexDocument(documentsToIndex, filename);
	}

}
