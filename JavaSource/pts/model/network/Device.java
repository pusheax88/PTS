package pts.model.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.model.network.properties.SnmpProperty;
import pts.model.network.transport.Interface;
import thewebsemantic.Namespace;

/**
 * NetworkElement with location and interfaces.
 *
 */
@Entity
@DiscriminatorValue("Device")
@Namespace(value="http://jscc.ru/pts#")
public class Device extends NetworkElement 
{
	@OneToOne(cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinColumn(name="LOCATION_ID", referencedColumnName="LOCATION_ID")
	private Location location;
	
	@OneToMany(cascade={CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(
            name="DEVICE_TO_INTERFACE",
            joinColumns = @JoinColumn( name="NETWORK_ELEMENT_ID"),
            inverseJoinColumns = @JoinColumn( name="CONNECTION_POINT_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Interface> interfaces;

	public Device(String name) 
	{
		super(name);
		this.interfaces = new ArrayList<Interface>();
	}
	
	public Device(String name, Collection<SnmpProperty> properties)
	{
		super(name, properties);
		this.interfaces = new ArrayList<Interface>();
	}
	
	public void setLocation(Location location) 
	{
		this.location = location;
	}

	public Location getLocation() 
	{
		return location;
	}

	public Collection<Interface> getInterfaces() 
	{
		return interfaces;
	}

	public void setInterfaces(Collection<Interface> interfaces) 
	{
		this.interfaces = interfaces;
	}
	
	public void addInterface(Interface... inter) 
	{
		if(interfaces == null)
		{
			interfaces = new ArrayList<Interface>();
		}
		for (int i = 0; i < inter.length; i++) 
		{
			this.interfaces.add(inter[i]);
		}
	}
	
	public void removeInterfacesForNames(String... interfacesNames) 
	{
		if(interfaces == null)
		{
			return;
		}
		for (int j = 0; j < interfacesNames.length; j++) 
		{
			for (Iterator<Interface> it = interfaces.iterator(); it.hasNext();) 
			{
				Interface inter = it.next();
				if(inter.getName() != null && inter.getName().equals(interfacesNames[j]))
				{
					it.remove();
				}
			}
		}
	}
	
}
