package pts.model.network;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pts.core.util.CommonTool;

import thewebsemantic.Namespace;

@Entity
@Table(name="LOCATION")
@Namespace(value="http://jscc.ru/pts#")
public class Location
{	
	@Id
	@Column(name = "LOCATION_ID")
	private Long id;

	private String name;
	
	public Location(String name)
	{
		this.name = name;
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
}
