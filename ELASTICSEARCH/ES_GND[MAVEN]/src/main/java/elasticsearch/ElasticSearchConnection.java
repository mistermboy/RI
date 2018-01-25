package elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;

import file.FileToLines;

public class ElasticSearchConnection extends ElasticSearch {

	private Client client;
	private String hostmane;
	private TransportClient transportClient;

	public ElasticSearchConnection() {
		super();
	}

	public ElasticSearchConnection(String clusterName) {
		super(clusterName);
	}

	public ElasticSearchConnection(String clusterName, int port) {
		super(clusterName, port);
	}

	public Client getConnection() {
		return this.client;
	}

	public ElasticSearchConnection setHostName(String hostmane) {
		this.hostmane = hostmane;
		return this;
	}

	@SuppressWarnings("resource")
	@Override
	public void connect() throws UnknownHostException {

		transportClient = new PreBuiltTransportClient(Settings.builder()
				.put("cluster.name", super.getClusterName())
				.put("client.transport.sniff", false)
				.put("client.transport.ping_timeout", 20, TimeUnit.SECONDS)
				.build());

		transportClient.addTransportAddress(new TransportAddress(
				InetAddress.getByName(this.hostmane), super.getPort()));

		this.client = transportClient;
	}

	public void executeQuery(String query) {

	}

	public void indexDocument() {
		BulkRequestBuilder bulkRequest = this.client.prepareBulk();
		bulkRequest.setRefreshPolicy(RefreshPolicy.NONE);
		FileToLines ftl = new FileToLines("2008-Feb-02-04-EN.json");

		int i = 0;
		double executed = 0;
		for (String str : ftl.lines()) {
			if (executed < 30000) {
				new TweetIndex(this, bulkRequest).createIndex(str)
						.getResponse();
				if (i >= 10000) {
					bulkRequest.get();
					bulkRequest = this.client.prepareBulk();
					bulkRequest.setRefreshPolicy(RefreshPolicy.NONE);
				}

				i++;
				executed++;
				System.out.println("Proceso: " + new DecimalFormat("#0.0000")
						.format(((double) (executed / 30000.0)) * 100));
			}
		}
		if (bulkRequest.numberOfActions() > 0)
			bulkRequest.get();
	}

	@Override
	public void disconnect() {
		this.client.close();
	}

	@Override
	public Client getClient() {
		return this.client;
	}
}
