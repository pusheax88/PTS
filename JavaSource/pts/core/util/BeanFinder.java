package pts.core.util;

import javax.faces.context.FacesContext;

public class BeanFinder
{
	public static <T> T findBean(String beanName, Class<T> beanClass) 
	{
	    FacesContext context = FacesContext.getCurrentInstance();
	    return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", beanClass);
	}
}
