package pts.model.network.properties;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import pts.core.util.CommonTool;
import thewebsemantic.Namespace;

@Entity
@Table(name="PROPERTY_DEFINITION")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("DEFAULT")
@Namespace(value="http://jscc.ru/pts#")
public class PropertyDefinition
{
	@Id
	@Column(name = "PROPERTY_DEF_ID")
	private Long id;
	
	@Column(name = "PROPERTY_DEF_NAME")
	private String name;
	
	public PropertyDefinition()
	{
	}
	
	public PropertyDefinition(String name)
	{
		this();
		this.name = name;
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
