package pts.model.network.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.model.network.NetworkElement;

@Entity
@Table(name="PROPERTY_HISTORY")
public class PropertyHistory
{
	private static Logger log = Logger.getLogger(PropertyHistory.class);
	@Id
	@GeneratedValue
	@Column(name = "HISTORY_ITEM_ID")
	private Long id;
	
	@ManyToOne
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
            name="HISTORY_ITEM_TO_NETWORK_ELEMENT",
            joinColumns = @JoinColumn( name="HISTORY_ITEM_ID"),
            inverseJoinColumns = @JoinColumn( name="NETWORK_ELEMENT_ID")
    )
	private NetworkElement networkElement;
	
	@ManyToMany
	@Cascade({CascadeType.ALL})
	@JoinTable(
            name="HISTORY_ITEM_TO_PROPERTY",
            joinColumns = @JoinColumn( name="HISTORY_ITEM_ID"),
            inverseJoinColumns = @JoinColumn( name="PROPERTY_VALUE_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<DatedPropertyValue> properties;
	
	public PropertyHistory()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public NetworkElement getNetworkElement()
	{
		return networkElement;
	}

	public void setNetworkElement(NetworkElement networkElement)
	{
		this.networkElement = networkElement;
	}

	public Collection<DatedPropertyValue> getProperties()
	{
		return properties;
	}

	public void setProperties(Collection<DatedPropertyValue> properties)
	{
		this.properties = properties;
	}
	
	public void addPropertyValue(DatedPropertyValue... pvs)
	{
		if(pvs == null || pvs.length == 0)
		{
			log.debug("Null argument action - returning");
			return;
		}
		if(properties == null)
		{
			properties = new ArrayList<DatedPropertyValue>();
		}
		properties.addAll(Arrays.asList(pvs));
	}
	
	public void removePropertyValue(DatedPropertyValue... pvs)
	{
		if(pvs == null || pvs.length == 0)
		{
			log.debug("Null argument action - returning");
			return;
		}
		for (int i = 0; i < pvs.length; i++)
		{
			for (Iterator iterator = properties.iterator(); iterator.hasNext();)
			{
				DatedPropertyValue prop = (DatedPropertyValue) iterator.next();
				if(prop.getId().equals(pvs[i].getId()))
				{
					iterator.remove();
				}
			}
		}
	}
}
