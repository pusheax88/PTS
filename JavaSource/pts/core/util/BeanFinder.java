package pts.core.util;

import javax.faces.context.FacesContext;

public class BeanFinder
{
	/**
	 * Finds existing bean or instantiates a new one.
	 * @param <T> Generic construct.
	 * @param beanName The name of the java bean to be loaded.
	 * @param beanClass The class of the java bean.
	 * @return bean instance.
	 */
	public static <T> T findBean(String beanName, Class<T> beanClass) 
	{
	    FacesContext context = FacesContext.getCurrentInstance();
	    return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", beanClass);
	}
}
