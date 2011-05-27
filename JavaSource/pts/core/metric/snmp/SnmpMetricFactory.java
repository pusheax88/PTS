package pts.core.metric.snmp;

public class SnmpMetricFactory
{
	public static SnmpMetric createSnmpMetric(int calculationResult)
	{
		return new SnmpMetric(calculationResult);
	}
}
