package pts.model.network.properties;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import thewebsemantic.Namespace;

@Entity
@DiscriminatorValue("DATED")
@Namespace(value="http://jscc.ru/pts#")
public class DatedPropertyValue extends PropertyValue
{
	@Column(name = "DATE")
	private Date date;
	
	public DatedPropertyValue()
	{
	}
	
	public DatedPropertyValue(Date date)
	{
		this(null, null, date);
	}
	
	public DatedPropertyValue(PropertyDefinition definition, String value, Date date)
	{
		super(definition, value);
		this.date = date;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
}
