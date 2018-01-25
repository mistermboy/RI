package elasticsearch;

import java.io.IOException;
import java.util.HashMap;

import org.elasticsearch.client.Client;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TweetIndex {

	private Client client;
	private IndexResponse response;
	private BulkRequestBuilder bulkRequest; 

	public TweetIndex( ElasticSearchConnection connection , BulkRequestBuilder bulkRequest ) {
		this.client = connection.getConnection();
		this.bulkRequest = bulkRequest;
	}

	@SuppressWarnings("unchecked")
	public TweetIndex createIndex( String JSONDocument ) {

		HashMap<String, Object> jsonAsMap = null;
		try {
			jsonAsMap = new ObjectMapper().readValue(JSONDocument,
					new TypeReference<HashMap<String, Object>>() {});
		} catch (IOException e) {
			System.err.println("Error while parsing the json document");
			e.printStackTrace();
		}

		this.bulkRequest.add(client
				.prepareIndex("tweets", "tweet",
						((HashMap<String, Object>) jsonAsMap.get("_source"))
								.get("id_str").toString())
				.setSource(((HashMap<String, Object>) jsonAsMap.get("_source"))));
		return this;
	}

	public IndexResponse getResponse() {
		return this.response;
	}
}
