package pts.core.dia;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import pts.core.dia.DiaAttribute.Point;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="object", namespace="http://www.lysator.liu.se/~alla/dia/")
public class DiaLine extends DiaObject
{
	@XmlElementRef
	private Connections connections;
	@XmlElementRef
	private DiaAttribute connEndpoints;
	
	public DiaLine()
	{
		super("Standard - Line", "0");
	}
	
	public DiaLine(DiaObject obj1, DiaObject obj2)
	{
		this(	obj1.getCentrePoint(), 
				obj2.getCentrePoint(), 
				new Connection("0", obj1.getId(), obj1.getConnection()),
				new Connection("1", obj2.getId(), obj2.getConnection()));
	}
	
	public DiaLine(Point start, Point end, Connection... connections)
	{
		super("Standard - Line", "0");
		this.connections = new Connections(connections);
		this.connEndpoints = new DiaAttribute("conn_endpoints", start, end);
	}
	
	public Connections getConnections()
	{
		return connections;
	}

	public void setConnections(Connections connections)
	{
		this.connections = connections;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name="connections", namespace="http://www.lysator.liu.se/~alla/dia/")
	public static class Connections
	{
		@XmlElementRef
		private Connection[] connections;
		
		public Connections()
		{
		}
		
		public Connections(Connection... connections)
		{
			this.connections = connections;
		}

		public Connection[] getConnections()
		{
			return connections;
		}

		public void setConnections(Connection[] connections)
		{
			this.connections = connections;
		}
		
		@Override
		public String toString()
		{
			return "Connections{" + connectionsToString() + "}";
		}

		private String connectionsToString()
		{
			if(connections != null)
			{
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < connections.length; i++)
				{
					sb.append("\n").append(connections[i]).append("; ");
				}
				return sb.toString();
			}
			else
			{
				return "None";
			}
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name="connection", namespace="http://www.lysator.liu.se/~alla/dia/")
	public static class Connection
	{
		@XmlAttribute
		private String handle;
		@XmlAttribute
		private String to;
		@XmlAttribute
		private String connection;
		
		public Connection()
		{
		}
		
		public Connection(String handle, String to, String connection)
		{
			this.handle = handle;
			this.to = to;
			this.connection = connection;
		}
		
		public String getHandle()
		{
			return handle;
		}
		public void setHandle(String handle)
		{
			this.handle = handle;
		}
		public String getTo()
		{
			return to;
		}
		public void setTo(String to)
		{
			this.to = to;
		}
		public String getConnection()
		{
			return connection;
		}
		public void setConnection(String connection)
		{
			this.connection = connection;
		}
		
		@Override
		public String toString()
		{
			return "Connection{handle=" + handle + "; to=" + to + "; connection=" + connection +  ";}";
		}
		
	}
	
	public String toString()
	{
		return "DiaLine{type=" + type + "; id = " + id + "; elem_corner=" + corner + "; \n " +
				"conn_endpoints=" + connEndpoints + "; \n" +
				"connections=" + connections + ";}";
	}
}
