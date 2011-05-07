package pts.core.util;

import pts.core.sql.IdGenerator;
import pts.core.sql.PgSqlIdGenerator;

public class CommonTool
{
	public static IdGenerator getIdGenerator()
	{
		IdGenerator idGenerator = PgSqlIdGenerator.getInstance();
		return idGenerator;
	}
}
