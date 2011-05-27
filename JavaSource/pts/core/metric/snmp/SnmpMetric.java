package pts.core.metric.snmp;

import pts.core.metric.Metric;
import pts.model.network.properties.PropertyDefinition;
import pts.model.network.properties.PropertyValue;

public class SnmpMetric implements Metric<Integer>
{
	private PropertyDefinition definition;
	private PropertyValue value;
	private Integer calculationResult = null;
	private boolean shouldRecalculate = false;
	
	public SnmpMetric(PropertyDefinition definition, PropertyValue value)
	{
		this.definition = definition;
		this.value = value;
	}
	
	SnmpMetric(Integer calculationResult)
	{
		this.calculationResult = calculationResult;
	}

	@Override
	public Integer calculate()
	{
		if(calculationResult == null || shouldRecalculate)
		{
			String seed = definition.getName() + value.getValue();
			calculationResult = seed.hashCode();
			shouldRecalculate = false;
		}
		
		return calculationResult;
	}

	public PropertyDefinition getDefinition()
	{
		return definition;
	}

	public void setDefinition(PropertyDefinition definition)
	{
		shouldRecalculate = true;
		this.definition = definition;
	}

	public PropertyValue getValue()
	{
		return value;
	}

	public void setValue(PropertyValue value)
	{
		shouldRecalculate = true;
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return "SnmpMetric{" + definition.getName() + value.getValue() +  ";" + calculationResult +  "}";
	}

}
