package pts.model.network.properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pts.core.util.CommonTool;

import thewebsemantic.Namespace;

/**
 * Simple Network Management Protocol property of network element.
 * Used as a metric in given network to distinguish between classes of problems.
 * 
 */
@Entity
@Table(name="SNMP_PROPERTY")
@Namespace(value="http://jscc.ru/pts#")
public class SnmpProperty extends ElementProperty<String>
{
	@Id
	@Column(name = "PROPERTY_ID")
	private Long id;
	
	private String mib;
	private String snmpPath;
	
	public SnmpProperty(String name, String path, String value)
	{
		setName(name);
		setSnmpPath(path);
		setValue(value);
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

	public String getMib() 
	{
		return mib;
	}
	
	public void setMib(String mib) 
	{
		this.mib = mib;
	}
	
	public String getSnmpPath() 
	{
		return snmpPath;
	}
	
	public void setSnmpPath(String snmpPath) 
	{
		this.snmpPath = snmpPath;
	}

	
}
