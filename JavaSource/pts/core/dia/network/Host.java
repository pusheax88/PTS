package pts.core.dia.network;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import pts.core.dia.DiaObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="object", namespace="http://www.lysator.liu.se/~alla/dia/")
public class Host extends DiaObject
{
	public Host()
	{
		this(null);
	}
	
	public Host(String corner)
	{
		super("Cisco - PC", "1", corner);
	}

}
