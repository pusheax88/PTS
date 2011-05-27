package pts.controller.network;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.util.CollectionUtils;

import pts.dao.network.PropertyHistoryDAO;
import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;
import pts.model.network.properties.PropertyHistory;

@ManagedBean(name="propertyHistoryManager")
@ApplicationScoped
public class DefaultPropertyHistoryManager implements PropertyHistoryManager
{
	@ManagedProperty(value="#{propertyHistoryDAO}")
	private PropertyHistoryDAO propertyHistoryDAO;
	
	public void setPropertyHistoryDAO(PropertyHistoryDAO propertyHistoryDAO)
	{
		this.propertyHistoryDAO = propertyHistoryDAO;
	}

	@Override
	public PropertyHistory getPropertyHistoryForNetworkElement(Long id)
	{
		return propertyHistoryDAO.getPropertyHistoryForNetworkElement(id);
	}

	@Override
	public void clearPropertyHistory(NetworkElement ne)
	{
		propertyHistoryDAO.clearPropertyHistory(ne);
	}

	@Override
	public void removePropertyValueFromHistory(NetworkElement ne, DatedPropertyValue... props)
	{
		propertyHistoryDAO.removePropertyValueFromHistory(ne, props);
	}

	@Override
	public void addPropertyValueToHistory(NetworkElement ne, DatedPropertyValue... props)
	{
		propertyHistoryDAO.addPropertyValueToHistory(ne, props);
	}

	@Override
	public Collection<DatedPropertyValue> getPropertyHistory(NetworkElement ne, PropertyDefinition propDef)
	{
		PropertyHistory ph = getPropertyHistoryForNetworkElement(ne.getId());
		
		Collection<DatedPropertyValue> result = new ArrayList<DatedPropertyValue>();
		
		if(ph != null && !CollectionUtils.isEmpty(ph.getProperties()))
		{
			for(DatedPropertyValue propVal : ph.getProperties())
			{
				if(propVal.getDefinition() != null && propDef != null && 
						propVal.getDefinition().getId().equals(propDef.getId()))
				{
					result.add(propVal);
				}
			}
		}
		
		return result;
	}

}
