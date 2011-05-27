package pts.core.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * JSF specific methods.
 * 
 */
public class JsfTool
{
	/**
	 * Boilerplate code to redirect to url.
	 * @param url
	 */
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
	
	/**
	 * Extracts request parameter.
	 * @param paramName
	 * @return
	 */
	public static Object getRequestParam(String paramName)
	{
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(paramName);
	}
}
