package pts.core.dia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="layer", namespace="http://www.lysator.liu.se/~alla/dia/")
public class DiaLayer
{
	@XmlElementRef
	private List<DiaObject> objects;
	
	@XmlAttribute
	private String name;
	@XmlAttribute
	private boolean visible;
	@XmlAttribute
	private boolean active;
	
	public DiaLayer()
	{
		name = "Фон";
		visible = true;
		active = true;
	}
	
	public DiaLayer(DiaObject... objects)
	{
		this();
		this.objects = Arrays.asList(objects);
	}

	public List<DiaObject> getObjects()
	{
		return objects;
	}

	public void setObjects(List<DiaObject> objects)
	{
		this.objects = objects;
	}
	
	public void addObjects(DiaObject... objects)
	{
		if(this.objects != null)
		{
			this.objects.addAll(Arrays.asList(objects));
		}
		else
		{
			this.objects = new ArrayList<DiaObject>();
			this.objects.addAll(Arrays.asList(objects));
		}
		
	}
	
	@Override
	public String toString()
	{
		return "DiaLayer{objects{" + objectsToString() + "}}";
	}

	private String objectsToString()
	{
		if(objects != null)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < objects.size(); i++)
			{
				sb.append("\n").append(objects.get(i)).append("; ");
			}
			return sb.toString();
		}
		else
		{
			return "None";
		}
	}

}
