package the4thamigouk.kafka.ksql;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ExecutorProperties {

	// configuration settings
	public static String APP_KSQL_QUERY = "ksql.query";

	// data members
	private final Properties props = new Properties();

	public void loadFromProperties(Properties props) {
		props.forEach((k, v) -> put(String.valueOf(k), String.valueOf(v)));
	}

	public void loadFromEnvironment(String envPrefix) {
		final Properties props = new Properties();
		System.getenv().forEach((k, v) -> {
			if (k.startsWith(envPrefix)) {
				final String name = k.replaceFirst(envPrefix, "").toLowerCase().replace('_', '.');
				props.put(name, v);
			}
		});
		loadFromProperties(props);
	}

	public void put(String name, String value) {
		this.props.put(name, value);
	}

	public Properties innerProps() {
		return this.props;
	}

	public Map<String, String> toMap() {
		return this.props.entrySet().stream()
				.collect(Collectors.toMap(e -> String.valueOf(e.getKey()), e -> String.valueOf(e.getValue())));
	}

	public String getKsqlQuery() {
		return props.getProperty(APP_KSQL_QUERY);
	}
}
