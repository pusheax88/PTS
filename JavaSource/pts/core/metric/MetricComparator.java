package pts.core.metric;

public interface MetricComparator<T extends Metric>
{
	/**
	 * Compare two metrics
	 * @returns true - metric value differences are relatively small.
	 */
	public boolean compareMetrics(T m1, T m2);
}
