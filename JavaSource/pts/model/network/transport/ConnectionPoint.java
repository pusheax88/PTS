/**
 * The means by which network elements connect to each other.
 */
package pts.model.network.transport;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.core.util.CommonTool;

import thewebsemantic.Namespace;

@Entity
@Table(name="CONNECTION_POINT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("ConnectionPoint")
@Namespace(value="http://jscc.ru/pts#")
public class ConnectionPoint
{
	@Id
	@Column(name = "CONNECTION_POINT_ID")
	private Long id;
	
	private String name;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(
            name="CP_TO_CP",
            joinColumns = @JoinColumn( name="CONNECTION_POINT_ID", referencedColumnName="CONNECTION_POINT_ID"),
            inverseJoinColumns = @JoinColumn( name="CONNECTED_TO", referencedColumnName="CONNECTION_POINT_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<ConnectionPoint> connectedTo;
	
	ConnectionPoint(String name)
	{
		this.name = name;
		this.connectedTo = new ArrayList<ConnectionPoint>();
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
	
	public Collection<ConnectionPoint> getConnectedTo() 
	{
		return connectedTo;
	}

	public void setConnectedTo(Collection<ConnectionPoint> connectedTo) 
	{
		this.connectedTo = connectedTo;
	}
	
	public void addConnectedTo(ConnectionPoint... entities) 
	{
		if(connectedTo == null)
		{
			connectedTo = new ArrayList<ConnectionPoint>();
		}
		for (int i = 0; i < entities.length; i++) 
		{
			this.connectedTo.add(entities[i]);
		}
	}
	
	public void removeConnectedToForNames(String... entities) 
	{
		if(connectedTo == null)
		{
			return;
		}
		for (int i = 0; i < entities.length; i++) 
		{
			for (Iterator<ConnectionPoint> it = connectedTo.iterator(); it.hasNext();) 
			{
				ConnectionPoint cp = it.next();
				if(cp.getName() != null && cp.getName().equals(entities[i]))
				{
					it.remove();
				}
			}
		}
	}
}
