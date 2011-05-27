package pts.core.metric.snmp;

import pts.core.metric.MetricComparator;

public class SnmpMetricComparator implements MetricComparator<SnmpMetric>
{
	public static Integer DEFAULT_METRIC_THRESHOLD = 20;
	private Integer metricThreshold;
	
	private static SnmpMetricComparator INSTANCE = new SnmpMetricComparator();
	
	public SnmpMetricComparator()
	{
		this(DEFAULT_METRIC_THRESHOLD);
	}
	
	public SnmpMetricComparator(int metricThreshold)
	{
		this.metricThreshold = metricThreshold;
	}
	
	public static SnmpMetricComparator getInstance()
	{
		return INSTANCE;
	}
	
	@Override
	public boolean compareMetrics(SnmpMetric m1, SnmpMetric m2)
	{
		return Math.abs(m1.calculate() - m2.calculate()) <= metricThreshold;
	}

}
