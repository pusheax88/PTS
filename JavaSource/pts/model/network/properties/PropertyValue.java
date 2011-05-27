package pts.model.network.properties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pts.core.util.CommonTool;
import thewebsemantic.Namespace;

@Entity
@Table(name="PROPERTY_VALUE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="type",
    discriminatorType=DiscriminatorType.STRING
)
@DiscriminatorValue("DEFAULT")
@Namespace(value="http://jscc.ru/pts#")
public class PropertyValue
{
	@Id
	@Column(name = "PROPERTY_VALUE_ID")
	private Long id;
	
	@Column(name = "PROPERTY_VALUE")
	private String value;
	
	@OneToOne(cascade=CascadeType.ALL)
	//@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinTable(
            name="PROPERTY_VALUE_TO_PROPERTY_DEFINITION",
            joinColumns = @JoinColumn( name="PROPERTY_VALUE_ID"),
            inverseJoinColumns = @JoinColumn( name="PROPERTY_DEF_ID")
    )
	private PropertyDefinition definition;
	
	public PropertyValue()
	{
	}
	
	public PropertyValue(PropertyDefinition definition, String value)
	{
		this.value = value;
		this.definition = definition;
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

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public PropertyDefinition getDefinition()
	{
		return definition;
	}

	public void setDefinition(PropertyDefinition definition)
	{
		this.definition = definition;
	}
	
}
