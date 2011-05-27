package pts.core.metric.extractor;

@Deprecated
public interface MetricExtractor<T, E>
{
	public E extract(T object);
}
