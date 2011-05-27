package pts.dao.network;

import java.util.Collection;

import pts.model.network.properties.PropertyValue;

public interface PropertyValueDAO
{
	public PropertyValue getPropertyValue(Long id);
	public Collection<PropertyValue> getPropertyValues(Long... ids);
	public void savePropertyValues(PropertyValue... props);
	public void deletePropertyValues(PropertyValue... props);
}
