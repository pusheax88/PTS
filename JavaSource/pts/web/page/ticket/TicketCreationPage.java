package pts.web.page.ticket;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pts.controller.ticket.TicketManager;
import pts.core.util.BeanFinder;
import pts.core.util.JsfTool;
import pts.model.ticket.Ticket;
import pts.model.ticket.TicketStatus;

@ManagedBean(name="ticketCreationPage")
@RequestScoped
public class TicketCreationPage
{
	public static String BEAN_NAME = "ticketCreationPage";
	
	public static TicketDetailsPage getInstance()
	{
		return (TicketDetailsPage)FacesContext.getCurrentInstance().
			getExternalContext().getRequestMap().get(BEAN_NAME);
	}
	
	private String ticketName;
	private String ticketDescription;

	public void setTicketName(String ticketName)
	{
		this.ticketName = ticketName;
	}

	public String getTicketName()
	{
		return ticketName;
	}
	
	public void setTicketDescription(String ticketDescription)
	{
		this.ticketDescription = ticketDescription;
	}

	public String getTicketDescription()
	{
		return ticketDescription;
	}
	
	//Navigation
	public void submitTicketAction()
	{
		TicketManager ticketManager = BeanFinder.findBean("ticketManager", TicketManager.class);
		
		Ticket t = new Ticket();
		t.setName(ticketName);
		t.setDescription(ticketDescription);
		t.setTicketDate(new Date());
		t.setStatus(TicketStatus.OPEN);
		ticketManager.saveTicket(t);
		
		JsfTool.redirect("/pages/tickets.jsf");
	}
	
	public String viewTicketsAction()
	{
		return "view";
	}
}
