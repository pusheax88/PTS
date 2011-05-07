package pts.core.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import pts.core.LoginBean;
import pts.core.util.BeanFinder;

public class LoggedInPhaseListener implements PhaseListener
{
	private static final long serialVersionUID = -1352835703720464607L;

	public PhaseId getPhaseId()
	{
		return PhaseId.RESTORE_VIEW;
	}

	public void beforePhase(PhaseEvent event)
	{
	}

	public void afterPhase(PhaseEvent event)
	{
		FacesContext fc = event.getFacesContext();
		LoginBean loginBean = BeanFinder.findBean("loginBean", LoginBean.class);

		// Check to see if they are on the login page.
		boolean loginPage = fc.getViewRoot().getViewId().lastIndexOf("login") > -1 ? true : false;
		if (!loginPage && !loginBean.isUserLoggedIn())
		{
			NavigationHandler nh = fc.getApplication().getNavigationHandler();
			nh.handleNavigation(fc, null, "logout");
		}
	}
}
