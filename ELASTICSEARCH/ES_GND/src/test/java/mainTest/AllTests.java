package mainTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import elasticsearch.ElasticSearch;
import elasticsearch.ElasticSearchConnection;
import elasticsearch.ElasticSearchQuery;
import file.FileToLines;

public class AllTests {

	ElasticSearch es = new ElasticSearchConnection("elasticsearch", 9300);

	@Before
	public void setUp() throws Exception {
		es.connect();
	}

	@After
	public void drop() throws Exception {
		es.getClient().close();
	}

	@SuppressWarnings({ "unused" })
	@Test
	@Ignore
	public void JSONtoMapTest() {
		FileToLines ftl = new FileToLines("2008-Feb-02-04.json");

	}

	@Test
	public void testElasticConection() throws Exception {
		System.out.println(es.getClient());
	}

	@Test
	public void indexTest() throws Exception {

		System.out.println(es.getClient());

		System.out.println("indexing...");

		//es.indexDocument();
		System.out.println("done...");

	}

	@Test
	public void searchTest() throws Exception {
		long a = System.currentTimeMillis();
		System.out.println("Hits for match (2672): "
				+ new ElasticSearchQuery(es.getClient()).getHitsOf("the")
						.getHits().totalHits);
		long b = System.currentTimeMillis();
		System.out.println("Time elapsed : " + (b - a));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void curlTest() throws Exception {
		URL url = new URL("http://localhost:9200/tweets/_search?pretty");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes("{\n" + "  \"query\": {\n" + "    \"match\": {\n"
				+ "      \"text\": \"super\"\n" + "    }\n" + "  },\n"
				+ "  \"aggregations\": {\n" + "    \"my_sample\": {\n"
				+ "      \"sampler\": {\n" + "        \"shard_size\": 10000\n"
				+ "      },\n" + "      \"aggregations\": {\n"
				+ "        \"keywords\": {\n"
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

		HashMap<String, Object> jsonAsMap = null;
		try {
			jsonAsMap = new ObjectMapper().readValue(response.toString(),
					new TypeReference<HashMap<String, Object>>() {});
		} catch (IOException e) {
			System.err.println("Error while parsing the json document");
			e.printStackTrace();
		}
		
		for(int i = 0; i < ((ArrayList) ((HashMap<String, Object>) ((HashMap<String, Object>) ((HashMap<String, Object>) jsonAsMap.get("aggregations")).get("my_sample")).get("keywords")).get("buckets")).size(); i++) {
			System.out.println(
					"related word : " + ((LinkedHashMap) ((ArrayList) ((HashMap<String, Object>) ((HashMap<String, Object>) ((HashMap<String, Object>) jsonAsMap.get("aggregations")).get("my_sample")).get("keywords")).get("buckets")).get(i)).get("key"));
		}
	}

}
