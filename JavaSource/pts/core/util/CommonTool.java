package pts.core.util;

import pts.core.sql.IdGenerator;
import pts.core.sql.PgSqlIdGenerator;

public class CommonTool
{
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
}
