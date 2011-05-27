package pts.controller.network;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import pts.dao.network.PropertyDefDAO;
import pts.model.network.properties.PropertyDefinition;

@ManagedBean(name="propertyDefManager")
@ApplicationScoped
public class DefaultPropertyManager implements PropertyManager
{
	private static Logger log = Logger.getLogger(DefaultPropertyManager.class);

	@ManagedProperty(value="#{PropertyDefDAO}")
	private PropertyDefDAO propertyDefDAO;
	
	public void setPropertyDefDAO(PropertyDefDAO propertyDefDAO)
	{
		this.propertyDefDAO = propertyDefDAO;
	}

	@Override
	public PropertyDefinition getPropertyDefinition(Long id)
	{
		if(id == null)
		{
			log.debug("Empty argument - return null");
			return null;
		}
		return propertyDefDAO.getPropertyDefinition(id);
	}

	@Override
	public Collection<PropertyDefinition> getPropertyDefinitions(Long... ids)
	{
		if(ArrayUtils.isEmpty(ids))
		{
			log.debug("Empty argument - return null");
			return null;
		}
		return propertyDefDAO.getPropertyDefinitions(ids);
	}

	@Override
	public void savePropertyDefinitions(PropertyDefinition... props)
	{
		propertyDefDAO.savePropertyDefinitions(props);
	}

	@Override
	public void deletePropertyDefinitions(PropertyDefinition... props)
	{
		propertyDefDAO.deletePropertyDefinitions(props);
	}

}
