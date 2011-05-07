package pts.web.page.ticket;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="ticketsPage")
@RequestScoped
public class TicketsPage
{
	public static final String BEAN_NAME = "ticketsPage";
	
	public static TicketsPage getInstance()
	{
		return (TicketsPage)FacesContext.getCurrentInstance().
			getExternalContext().getRequestMap().get(BEAN_NAME);
	}
}
