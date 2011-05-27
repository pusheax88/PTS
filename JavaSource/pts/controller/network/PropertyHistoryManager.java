package pts.controller.network;

import java.util.Collection;

import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;
import pts.model.network.properties.PropertyHistory;

public interface PropertyHistoryManager
{
	public PropertyHistory getPropertyHistoryForNetworkElement(Long id);
	public Collection<DatedPropertyValue> getPropertyHistory(NetworkElement ne, PropertyDefinition propDef);
	public void clearPropertyHistory(NetworkElement ne);
	void removePropertyValueFromHistory(NetworkElement ne, DatedPropertyValue... props);
	void addPropertyValueToHistory(NetworkElement ne, DatedPropertyValue... props);
}
