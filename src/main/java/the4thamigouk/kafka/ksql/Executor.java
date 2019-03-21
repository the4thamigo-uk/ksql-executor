package the4thamigouk.kafka.ksql;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.StreamsConfig;

import io.confluent.ksql.KsqlContext;
import io.confluent.ksql.function.UdfLoader;
import io.confluent.ksql.util.KsqlConfig;

public class Executor {
	public static void main (final String[] args) {
		final String ksql = args[0];
		
		final Map<String, String> props = new HashMap<String, String>();
		props.put(KsqlConfig.SCHEMA_REGISTRY_URL_PROPERTY, "http://schema-registry:8081");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:9092");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		System.out.println(props.toString());
		final KsqlConfig cfg = new KsqlConfig(props);

		System.out.println(cfg.toString());
		final KsqlContext ctx = KsqlContext.create(cfg);

	    UdfLoader.newInstance(cfg, ctx.getMetaStore(), ".").load();

	    ctx.sql(ksql);
	}
}
