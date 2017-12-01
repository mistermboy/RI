package elasticsearch;

public abstract class ElasticSearch implements IElasticSearch {

	private String clusterName = "elasticsearch";
	private int port = 9300;

	public ElasticSearch() {}

	public ElasticSearch(String clusterName) {
		this.clusterName = clusterName;
	}

	public ElasticSearch(String clusterName, int port) {
		this(clusterName);
		this.port = port;
	}

	/**
	 * @return the clusterName
	 */
	public String getClusterName() {
		return clusterName;
	}

	/**
	 * @param clusterName the clusterName to set
	 */
	void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
}
