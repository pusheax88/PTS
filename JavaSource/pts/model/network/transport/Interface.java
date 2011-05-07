package pts.model.network.transport;

import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import thewebsemantic.Namespace;

@Entity
@DiscriminatorValue("Interface")
@Namespace(value="http://jscc.ru/pts#")
public class Interface extends ConnectionPoint 
{
	
	public Interface(String name)
	{
		super(name);
	}
	
	public Interface(String name, Collection<ConnectionPoint> connectedTo)
	{
		super(name);
		setConnectedTo(connectedTo);
	}

}
