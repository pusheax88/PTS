package pts.core.dia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import pts.core.dia.DiaAttribute.Point;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="object", namespace="http://www.lysator.liu.se/~alla/dia/")
public abstract class DiaObject
{
	private static Logger log = Logger.getLogger(DiaObject.class);
	
	@XmlAttribute
	protected String type;
	@XmlAttribute
	protected String version;
	@XmlAttribute
	protected String id;
	
	@XmlElementRef
	protected DiaAttribute corner;
	
	public DiaObject()
	{
	}
	
	public DiaObject(String type, String version)
	{
		this(type, version, null);
	}
	
	public DiaObject(String type, String version, String corner)
	{
		this.type = type;
		this.version = version;
		this.id = DiaID.generateID();
		this.corner = new DiaAttribute("elem_corner", new Point(corner));
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public DiaAttribute getCorner()
	{
		return corner;
	}

	public void setCorner(DiaAttribute corner)
	{
		this.corner = corner;
	}
	
	public Point getCentrePoint()
	{
		if(corner == null || corner.getPoints() == null || corner.getPoints().length == 0)
		{
			return new Point("1,1");
		}
		
		Point cornerPoint = corner.getPoints()[0];
		String pointCoord = cornerPoint.getVal();
		
		if(pointCoord == null || pointCoord.split(",") == null || pointCoord.split(",").length != 2)
		{
			return new Point("1,1");
		}
		
		int pointX = Integer.valueOf(pointCoord.split(",")[0].trim());
		int pointY = Integer.valueOf(pointCoord.split(",")[1].trim());
		
		return new Point( ++pointX + "," + ++pointY);
	}
	
	public String getConnection()
	{
		return "10";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof DiaObject)
		{
			return id != null && id.equals(((DiaObject) obj).getId());
		}
			
		return super.equals(obj);
	}
	
	@Override
	public String toString()
	{
		return "DiaObject{type=" + type + "; id = " + id + "; elem_corner=" + corner + "}";
	}
	
}
