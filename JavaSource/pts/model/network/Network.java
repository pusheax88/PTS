package pts.model.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.core.util.CommonTool;
import thewebsemantic.Namespace;

/**
 * The root of network model hierarchy.
 * Simple implementation of network, which contains only network elements.
 * Uses Hibernate annotations for persistence. 
 * @Id annotaiton provides SQL PK and unique URI suffix for Jenabean.
 * @see NetworkElement
 */
@Entity
@Table(name="NETWORK")
@Namespace(value="http://jscc.ru/pts#")
public class Network 
{
	@Id
	@Column(name = "NETWORK_ID")
	private Long id;
	
	private String name;
	private String description;
	
	@OneToMany(cascade={CascadeType.ALL})
	//@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinTable(
            name="NETWORK_TO_NETWORK_ELEMENT",
            joinColumns = @JoinColumn( name="NETWORK_ID"),
            inverseJoinColumns = @JoinColumn( name="NETWORK_ELEMENT_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
	private Collection<NetworkElement> networkElements;
	
	public Network(String name, Collection<NetworkElement> networkElements)
	{
		this.name = name;
		this.networkElements = networkElements;
		id = CommonTool.getIdGenerator().generateID();
	}
	
	public Network(String name)
	{
		this(name, new ArrayList<NetworkElement>());
	}
	
	public Network()
	{
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Collection<NetworkElement> getNetworkElements()
	{
		return networkElements;
	}

	public void setNetworkElements(Collection<NetworkElement> networkElements)
	{
		this.networkElements = networkElements;
	}

	public void addNetworkElements(NetworkElement... entities) 
	{
		if(networkElements == null)
		{
			networkElements = new ArrayList<NetworkElement>();
		}
		for (int i = 0; i < entities.length; i++) 
		{
			this.networkElements.add(entities[i]);
		}
	}
	
	public void removeNetworkElementsForNames(String... entityNames) 
	{
		if(networkElements == null)
		{
			return;
		}
		for (int i = 0; i < entityNames.length; i++) 
		{
			for (Iterator<NetworkElement> it = networkElements.iterator(); it.hasNext();) 
			{
				NetworkElement e = it.next();
				if( e.getName() != null && e.getName().equals(entityNames[i]) )
				{
					it.remove();
				}
			}
		}
	}
	
	@Override
	public String toString()
	{
		return "Network{" + name + "}";
	}
}
