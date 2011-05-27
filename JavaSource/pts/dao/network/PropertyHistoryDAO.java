package pts.dao.network;

import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyHistory;

public interface PropertyHistoryDAO
{
	public PropertyHistory getPropertyHistoryForNetworkElement(Long id);
	public void clearPropertyHistory(NetworkElement ne);
	void removePropertyValueFromHistory(NetworkElement ne, DatedPropertyValue... props);
	void addPropertyValueToHistory(NetworkElement ne, DatedPropertyValue... props);
}
