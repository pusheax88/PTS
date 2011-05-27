package pts.core.dia.network;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import pts.core.dia.DiaObject;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="object", namespace="http://www.lysator.liu.se/~alla/dia/")
public class Router extends DiaObject
{
	public Router()
	{
		this(null);
	}
	
	public Router(String corner)
	{
		super("Cisco - Router", "1", corner);
	}
	
	@Override
	public String getConnection()
	{
		return "0";
	}
}
