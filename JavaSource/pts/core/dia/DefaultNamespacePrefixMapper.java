package pts.core.dia;

import java.util.HashMap;
import java.util.Map;

public class DefaultNamespacePrefixMapper extends com.sun.xml.bind.marshaller.NamespacePrefixMapper
{
	private Map<String, String> uriToPrefixMap = new HashMap<String, String>();
	public DefaultNamespacePrefixMapper()
	{
		super();
		uriToPrefixMap.put("http://www.lysator.liu.se/~alla/dia/", "dia");
	}

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix)
	{
		return uriToPrefixMap.get(namespaceUri);
	}

}
