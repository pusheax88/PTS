package pts.core.metric.network;

import pts.core.metric.MetricComparator;

public class TicketMetricComparator implements MetricComparator<TicketMetric>
{
	public static Integer DEFAULT_METRIC_THRESHOLD = 20;
	private Integer metricThreshold;
	
	private static TicketMetricComparator INSTANCE = new TicketMetricComparator();
	
	public TicketMetricComparator()
	{
		this(DEFAULT_METRIC_THRESHOLD);
	}
	
	public TicketMetricComparator(int metricThreshold)
	{
		this.metricThreshold = metricThreshold;
	}
	
	public static TicketMetricComparator getInstance()
	{
		return INSTANCE;
	}
	
	@Override
	public boolean compareMetrics(TicketMetric m1, TicketMetric m2)
	{
		return Math.abs(m1.calculate() - m2.calculate()) <= metricThreshold;
	}

}
