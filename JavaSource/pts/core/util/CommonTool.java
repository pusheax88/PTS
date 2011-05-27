package pts.core.util;

import org.apache.log4j.Logger;

import pts.core.sql.IdGenerator;
import pts.core.sql.PgSqlIdGenerator;

public class CommonTool
{
	private static Logger log = Logger.getLogger(CommonTool.class);
	
	/**
	 * Gets IdGenerator implementation.
	 * Proxy method to provide another layer of abstraction.
	 * @return IdGenerator implementation
	 */
	public static IdGenerator getIdGenerator()
	{
		IdGenerator idGenerator = PgSqlIdGenerator.getInstance();
		return idGenerator;
	}
	
	public static Long toLong(String val)
	{
		try
		{
			return Long.valueOf(val);
		}
		catch(Exception e)
		{
			log.debug("Exception while trying to convert value " + val + "to Long. Return null.");
			return null;
		}
	}
}
