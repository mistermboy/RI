package uo.ri.conf;

import java.io.IOException;
import java.util.Properties;

public class Conf {

	private static final String CONF_FILE = "configuration.properties";

	private static Conf instance;
	private Properties properties; // Es el import correco?

	private Conf() {
		properties = new Properties();
		try {
			properties.load(Conf.class.getClassLoader().getResourceAsStream(CONF_FILE));
		} catch (IOException e) {
			throw new RuntimeException("Properties file can not be loaded", e);
		}
	}

	public static String get(String key) {
		return getInstance().getProperty(key);
	}

	private String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Properties not found in config file");
		}
		return value;
	}

	private static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}

}
