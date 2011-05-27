package pts.core.metric.gather;

import java.util.Map;

import pts.model.network.properties.PropertyDefinition;

public interface MetricGatherer<T, E>
{
	public Map<PropertyDefinition, E> gather(T t);
}
