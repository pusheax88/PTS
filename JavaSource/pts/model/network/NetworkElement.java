package pts.model.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.core.util.CommonTool;
import pts.model.network.properties.SnmpProperty;
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
	
	@OneToMany(cascade={CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(
            name="NETWORK_ELEMENT_TO_PROPERTY",
            joinColumns = @JoinColumn( name="NETWORK_ELEMENT_ID"),
            inverseJoinColumns = @JoinColumn( name="PROPERTY_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<SnmpProperty> properties;
	
	public NetworkElement()
	{
		this("Default Network Element", new ArrayList<SnmpProperty>());
	}
	
	public NetworkElement(String name)
	{
		this(name, new ArrayList<SnmpProperty>());
	}
	
	public NetworkElement(String name, Collection<SnmpProperty> properties)
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

	public Collection<SnmpProperty> getProperties() 
	{
		return properties;
	}

	public void setProperties(Collection<SnmpProperty> properties) 
	{
		this.properties = properties;
	}
	
	public void addProperties(Collection<SnmpProperty> properties) 
	{
		if(this.properties == null)
		{
			this.properties = new ArrayList<SnmpProperty>();
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
			for (Iterator<SnmpProperty> it = properties.iterator(); it.hasNext();) 
			{
				SnmpProperty ep = it.next();
				if(ep.getName() != null && ep.getName().equals(props[i]))
				{
					it.remove();
				}
			}
		}
	}

}
