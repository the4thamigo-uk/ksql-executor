package the4thamigouk.kafka.ksql;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import io.confluent.ksql.KsqlContext;
import io.confluent.ksql.function.UdfFactory;
import io.confluent.ksql.function.UdfLoader;
import io.confluent.ksql.metastore.MetaStore;
import io.confluent.ksql.util.KsqlConfig;

public class Executor {

	private static final Logger log = Logger.getLogger(Executor.class.getName());

	public static void main(final String[] args) {

		// get the properties
		final ExecutorProperties props = new ExecutorProperties();
		props.loadFromEnvironment("APP_");
		log.log(Level.INFO, "Properties: " + props.toString());

		final String ksql = args[0].trim();
		if(ksql != null && ksql != "") {
			props.put(ExecutorProperties.APP_KSQL_QUERY, ksql);
		}
q
		// load the KSQL config
		final KsqlConfig cfg = new KsqlConfig(props.innerProps());
		log.log(Level.INFO, "KSQL configuration: " + cfg.toString());

		// instantiate the KSQL engine
		final KsqlContext ctx = KsqlContext.create(cfg);

		// load udfs into engine
		final MetaStore metastore = ctx.getMetaStore();
		UdfLoader.newInstance(cfg, metastore, props.getUDFPath()).load();
		log.log(Level.INFO, "UDFs: "
				+ metastore.listFunctions().stream().map(UdfFactory::getName).collect(Collectors.joining(",")));

		// run the query
		ctx.sql(props.getKsqlQuery());
	}
}
