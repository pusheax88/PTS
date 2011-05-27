package pts.core.dia;

public class DiaID
{
	private static int id = 0;
	private static int handle = 0;
	
	public static String generateID()
	{
		return new String(String.valueOf(++id));
	}
	
	public static String generateHandle()
	{
		return new String(String.valueOf(++handle));
	}
}
