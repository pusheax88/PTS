package pts.core.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class JsfTool
{
	public static void redirect(String url)
	{
		try
		{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect(externalContext.encodeResourceURL(externalContext.getRequestContextPath() + url));
		}
		catch(Exception e)
		{
			throw new RuntimeException("Redirect Exception", e);
		}
	}
	
	public static Object getRequestParam(String paramName)
	{
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(paramName);
	}
}
