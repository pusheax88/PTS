package pts.core.metric.gather;

import java.util.HashMap;
import java.util.Map;

import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;

public class NetworkMetricGatherer implements MetricGatherer<NetworkElement, DatedPropertyValue>
{
	private MetricGatherer<PropertyDefinition, DatedPropertyValue> snmpMetricGatherer;
	
	NetworkMetricGatherer(MetricGatherer<PropertyDefinition, DatedPropertyValue> snmpMetricGatherer)
	{
		this.snmpMetricGatherer = snmpMetricGatherer;
	}
	
	@Override
	public Map<PropertyDefinition, DatedPropertyValue> gather(NetworkElement ne)
	{
		Map<PropertyDefinition, DatedPropertyValue> result = new HashMap<PropertyDefinition, DatedPropertyValue>();
		
		
		for (PropertyDefinition propDef : ne.getProperties())
		{
			result.putAll(snmpMetricGatherer.gather(propDef));
		}
		
		return result;
	}
}
