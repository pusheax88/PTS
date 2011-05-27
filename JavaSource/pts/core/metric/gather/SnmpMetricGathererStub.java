package pts.core.metric.gather;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;

public class SnmpMetricGathererStub implements MetricGatherer<PropertyDefinition, DatedPropertyValue>
{

	@Override
	public Map<PropertyDefinition, DatedPropertyValue> gather(PropertyDefinition propDef)
	{
		Map<PropertyDefinition, DatedPropertyValue> result = new HashMap<PropertyDefinition, DatedPropertyValue>();
		
		result.put(propDef, gatherInternal(propDef));
		
		return result;
	}
	
	private DatedPropertyValue gatherInternal(PropertyDefinition propDef)
	{
		String value = String.valueOf(Math.random() * 1000000000);
		DatedPropertyValue propVal = new DatedPropertyValue(propDef, value, new Date());
		return propVal;
	}

}
