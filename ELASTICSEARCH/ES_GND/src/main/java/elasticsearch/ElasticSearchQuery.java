package elasticsearch;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchQuery {

	private Client client;

	public ElasticSearchQuery(Client client) {
		this.client = client;
	}

	public SearchResponse getHitsOf(String term) {
		return client.prepareSearch().setTypes()
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setPostFilter(QueryBuilders.matchQuery("text", term)).execute()
				.actionGet();
	}

	public StringBuffer getRelatedTerms(String text) throws Exception {
		URL url = new URL("http://localhost:9200/tweets/_search?pretty");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes("{\n" + "  \"query\": {\n" + "    \"match\": {\n"
				+ "      \"text\": {\n" + "        \"query\": \"" + text
				+ "\",\n" + "        \"operator\": \"and\"\n" + "      }\n"
				+ "    }\n" + "  },\n" + "  \"aggs\": {\n"
				+ "    \"sample\": {\n" + "      \"sampler\": {\n"
				+ "        \"shard_size\": 10000\n" + "      },\n"
				+ "      \"aggs\": {\n" + "        \"keywords\": {\n"
				+ "          \"significant_text\": {\n"
				+ "            \"field\": \"text\",\n"
				+ "            \"filter_duplicate_text\": false\n"
				+ "          }\n" + "        }\n" + "      }\n" + "    }\n"
				+ "  }\n" + "}");
		wr.flush();
		wr.close();

		BufferedReader iny = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = iny.readLine()) != null) {
			response.append(output);
		}
		iny.close();

		return response;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void parseResponse(StringBuffer response) {
		HashMap<String, Object> jsonAsMap = null;
		try {
			jsonAsMap = new ObjectMapper().readValue(response.toString(),
					new TypeReference<HashMap<String, Object>>() {});
		} catch (IOException e) {
			System.err.println("Error while parsing the json document");
			e.printStackTrace();
		}

		for (int i = 0; i < ((ArrayList) ((HashMap<String, Object>) ((HashMap<String, Object>) ((HashMap<String, Object>) jsonAsMap
				.get("aggregations")).get("sample")).get("keywords"))
						.get("buckets")).size(); i++) {
			System.out.println("related word : "
					+ ((LinkedHashMap) ((ArrayList) ((HashMap<String, Object>) ((HashMap<String, Object>) ((HashMap<String, Object>) jsonAsMap
							.get("aggregations")).get("sample"))
									.get("keywords")).get("buckets"))
											.get(i)).get("key"));
		}
	}

}
