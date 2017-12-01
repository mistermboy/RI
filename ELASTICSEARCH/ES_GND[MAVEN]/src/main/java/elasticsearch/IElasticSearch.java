package elasticsearch;

import org.elasticsearch.client.Client;

public interface IElasticSearch {
	
	public void connect() throws Exception;
	
	public void disconnect();
	
	public Client getClient();
	
	public void indexDocument();

}
