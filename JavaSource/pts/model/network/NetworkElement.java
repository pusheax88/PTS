package pts.model.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.core.util.CommonTool;
import pts.model.network.properties.SnmpPropertyDefinition;
import thewebsemantic.Namespace;

/**
 * Represents any network element. Extend it to provide specific implementation.
 *
 */
@Entity
@Table(name="NETWORK_ELEMENT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("NetworkElement")
@Namespace(value="http://jscc.ru/pts#")
public class NetworkElement 
{
	@Id
	@Column(name = "NETWORK_ELEMENT_ID")
	private Long id;
	
	private String name;
	private String elementType;
	
	@OneToMany
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
            name="NETWORK_ELEMENT_TO_PROPERTY",
            joinColumns = @JoinColumn( name="NETWORK_ELEMENT_ID"),
            inverseJoinColumns = @JoinColumn( name="PROPERTY_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<SnmpPropertyDefinition> properties;
	
	public NetworkElement()
	{
	}
	
	public NetworkElement(String name)
	{
		this(name, new ArrayList<SnmpPropertyDefinition>());
	}
	
	public NetworkElement(String name, Collection<SnmpPropertyDefinition> properties)
	{
		this.name = name;
		this.properties = properties;
		id = CommonTool.getIdGenerator().generateID();
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long ticketID)
	{
		this.id = ticketID;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getElementType()
	{
		if(elementType != null)
		{
			return elementType;
		}
		return "NetworkElement";
	}

	public void setElementType(String elementType)
	{
		this.elementType = elementType;
	}

	public Collection<SnmpPropertyDefinition> getProperties() 
	{
		return properties;
	}

	public void setProperties(Collection<SnmpPropertyDefinition> properties) 
	{
		this.properties = properties;
	}
	
	public void addProperties(Collection<SnmpPropertyDefinition> properties) 
	{
		if(this.properties == null)
		{
			this.properties = new ArrayList<SnmpPropertyDefinition>();
		}
		this.properties.addAll(properties);
	}
	
	public void removePropertiesForNames(String... props) 
	{
		if(properties == null)
		{
			return;
		}
		for (int i = 0; i < props.length; i++) 
		{
			for (Iterator<SnmpPropertyDefinition> it = properties.iterator(); it.hasNext();) 
			{
				SnmpPropertyDefinition ep = it.next();
				if(ep.getName() != null && ep.getName().equals(props[i]))
				{
					it.remove();
				}
			}
		}
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(getId() != null && obj != null && obj instanceof NetworkElement)
		{
			return getId().equals(((NetworkElement)obj).getId());
		}
		return super.equals(obj);
	}
	
	@Override
	public String toString()
	{
		return "NetworkElement{name=" + name + "; id=" + id + "}";
	}

}
