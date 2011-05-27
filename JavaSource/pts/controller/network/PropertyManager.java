package pts.controller.network;

import java.util.Collection;

import pts.model.network.properties.PropertyDefinition;

public interface PropertyManager
{
	public PropertyDefinition getPropertyDefinition(Long id);
	public Collection<PropertyDefinition> getPropertyDefinitions(Long... ids);
	public void savePropertyDefinitions(PropertyDefinition... propDefs);
	public void deletePropertyDefinitions(PropertyDefinition... propDefs);
}
