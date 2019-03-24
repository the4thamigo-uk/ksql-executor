package the4thamigouk.kafka.ksql.udf;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

@UdfDescription(name = "iif", description = "If predicate returns true return first value else return the second")
public class Iif {

	@Udf
	public String iif(final boolean predicate, final String trueValue, final String falseValue) {
		return iif_impl(predicate, trueValue, falseValue);
	}

	@Udf
	public Boolean iif(final boolean predicate, final Boolean trueValue, final Boolean falseValue) {
		return iif_impl(predicate, trueValue, falseValue);
	}

	@Udf
	public Long iif(final boolean predicate, final Long trueValue, final Long falseValue) {
		return iif_impl(predicate, trueValue, falseValue);
	}

	@Udf
	public Integer iif(final boolean predicate, final Integer trueValue, final Integer falseValue) {
		return iif_impl(predicate, trueValue, falseValue);
	}

	@Udf
	public Double iif(final boolean predicate, final Double trueValue, final Double falseValue) {
		return iif_impl(predicate, trueValue, falseValue);
	}

	private <T> T iif_impl(final boolean predicate, final T trueValue, final T falseValue) {
		return predicate ? trueValue : falseValue;
	}
}