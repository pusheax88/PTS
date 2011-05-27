package pts.core.metric.gather;

import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;

public class MetricGathererFactory
{
	public static MetricGatherer<NetworkElement, DatedPropertyValue> createNetworkMetricGatherer()
	{
		//For testing purposes create NetworkMetricGatherer with stub snmp metric gatherer
		NetworkMetricGatherer res = new NetworkMetricGatherer(new SnmpMetricGathererStub());
		return res;
	}
}
