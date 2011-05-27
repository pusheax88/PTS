package pts.core.dia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="attribute", namespace="http://www.lysator.liu.se/~alla/dia/")
public class DiaAttribute
{
	@XmlAttribute
	private String name;
	@XmlElementRef
	private Point[] points;
	
	public DiaAttribute()
	{
	}
	
	public DiaAttribute(String name, Point... points)
	{
		this.name = name;
		this.points = points;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Point[] getPoints()
	{
		return points;
	}

	public void setPoints(Point[] points)
	{
		this.points = points;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name="point", namespace="http://www.lysator.liu.se/~alla/dia/")
	public static class Point
	{
		@XmlAttribute
		private String val;
		
		public Point()
		{
		}
		
		public Point(String val)
		{
			this.val = val;
		}

		public String getVal()
		{
			return val;
		}

		public void setVal(String val)
		{
			this.val = val;
		}
		
		@Override
		public String toString()
		{
			return "Point{coord=" + val + "}";
		}
		
	}
	
	@Override
	public String toString()
	{
		return "DiaAttribute{points{" + pointsToString() + "}}";
	}

	private String pointsToString()
	{
		if(points != null)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < points.length; i++)
			{
				sb.append("\n").append(points[i]).append("; ");
			}
			return sb.toString();
		}
		else
		{
			return "None";
		}
	}
}
