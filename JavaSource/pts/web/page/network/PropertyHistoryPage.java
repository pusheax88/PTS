package pts.web.page.network;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import pts.controller.network.PropertyHistoryManager;
import pts.core.util.CommonTool;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;
import pts.model.network.properties.PropertyHistory;

@ManagedBean(name="propertyHistoryPage")
@RequestScoped
public class PropertyHistoryPage
{
	private static Logger log = Logger.getLogger(PropertyHistoryPage.class);
	
	@ManagedProperty(value="#{propertyHistoryManager}")
	private PropertyHistoryManager propertyHistoryManager;
	
	private String propertyDefinitionID, networkElementID;
	private Collection<DatedPropertyValue> properties;
	private PropertyHistory ph;
	
	public PropertyHistoryPage()
	{
		networkElementID = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("networkElementID");
		
		propertyDefinitionID = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("propertyDefinitionID");
	}
	
	@PostConstruct
	private void init()
	{
		if(!StringUtils.isEmpty(propertyDefinitionID) && !StringUtils.isEmpty(networkElementID))
		{
			ph = propertyHistoryManager.getPropertyHistoryForNetworkElement(
					Long.valueOf(networkElementID));
			log.debug("Resolved PropertyHistory " + ph);
		}
		else
		{
			log.debug("Either propertyDefinitionID or networkElementID is empty");
		}
	}
	
	public void setPropertyHistoryManager(PropertyHistoryManager propertyHistoryManager)
	{
		this.propertyHistoryManager = propertyHistoryManager;
	}
	
	public Collection<DatedPropertyValue> getProperties()
	{
		if(ph == null)
		{
			log.debug("PropertyHistory was not initialized");
			return null;
		}
		Collection<DatedPropertyValue> props = ph.getProperties();
		if(properties == null)
		{
			properties = new ArrayList<DatedPropertyValue>();
			for(DatedPropertyValue prop : props)
			{
				PropertyDefinition pd = prop.getDefinition();
				if(pd != null && pd.getId().equals(CommonTool.toLong(propertyDefinitionID)))
				{
					properties.add(prop);
				}
			}
		}
		
		return properties;
	}

	public String getPropertyDefinitionID()
	{
		return propertyDefinitionID;
	}

	public void setPropertyDefinitionID(String propertyDefinitionID)
	{
		this.propertyDefinitionID = propertyDefinitionID;
	}

	public PropertyHistory getPh()
	{
		return ph;
	}

	public void setPh(PropertyHistory ph)
	{
		this.ph = ph;
	}
	
}
