package pts.core.dia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="diagram", namespace="http://www.lysator.liu.se/~alla/dia/")
public class DiaDiagram
{
	@XmlElementRef
	private DiaLayer[] layers;
	
	public DiaDiagram()
	{
	}
	
	public DiaDiagram(DiaLayer... layers)
	{
		this.layers = layers;
	}

	public DiaLayer[] getLayers()
	{
		return layers;
	}

	public void setObjects(DiaLayer[] layers)
	{
		this.layers = layers;
	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "DiaDiagram{layers{" + layersToString() + "}}";
	}
	
	private String layersToString()
	{
		if(layers != null)
		{
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < layers.length; i++)
			{
				sb.append("\n").append(layers[i].toString()).append("; ");
			}
			return sb.toString();
		}
		else
		{
			return "None";
		}
	}

}
