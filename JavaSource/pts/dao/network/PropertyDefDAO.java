package pts.dao.network;

import java.util.Collection;

import pts.model.network.properties.PropertyDefinition;

public interface PropertyDefDAO
{
	public PropertyDefinition getPropertyDefinition(Long id);
	public Collection<PropertyDefinition> getPropertyDefinitions(Long... ids);
	public void savePropertyDefinitions(PropertyDefinition... propDefs);
	public void deletePropertyDefinitions(PropertyDefinition... propDefs);
}
