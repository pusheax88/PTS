package pts.model.network.properties;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import thewebsemantic.Namespace;

/**
 * Simple Network Management Protocol property of network element.
 * Used as a metric in given network to distinguish between classes of problems.
 * 
 */
@Entity
@Table(name="SNMP_PROPERTY")
@DiscriminatorValue("SNMP")
@Namespace(value="http://jscc.ru/pts#")
public class SnmpPropertyDefinition extends PropertyDefinition
{	
	@Column(name = "MIB")
	private String mib;
	
	@Column(name = "SNMP_PATH")
	private String snmpPath;
	
	public SnmpPropertyDefinition(String name, String path)
	{
		super(name);
		setSnmpPath(path);
	}
	
	public SnmpPropertyDefinition()
	{
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
