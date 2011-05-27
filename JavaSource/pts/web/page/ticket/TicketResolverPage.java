package pts.web.page.ticket;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import pts.controller.ticket.TicketManager;
import pts.core.util.CommonTool;
import pts.core.util.JsfTool;
import pts.model.ticket.Ticket;

@ManagedBean(name="ticketResolverPage")
@RequestScoped
public class TicketResolverPage
{
	private static Logger log = Logger.getLogger(TicketResolverPage.class);
	
	@ManagedProperty(value="#{ticketManager}")
	private TicketManager ticketManager;
	private String ticketID;
	private Collection<Ticket> similarTickets;
	
	public TicketResolverPage()
	{
		ticketID = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("ticketID");
		
		if(StringUtils.isEmpty(ticketID))
		{
			JsfTool.redirect("/pages/error.jsf?error=Illegal+argument+exception");
		}
	}

	public void setTicketManager(TicketManager ticketManager)
	{
		this.ticketManager = ticketManager;
	}

	public Collection<Ticket> getSimilarTickets()
	{
		if(similarTickets == null)
		{
			log.debug("Resolving similar tickets");
			similarTickets = ticketManager.findSimilarTicket(
					ticketManager.getTicket(CommonTool.toLong(ticketID)));
		}
		return similarTickets;
	}

	public void setSimilarTickets(Collection<Ticket> similarTickets)
	{
		this.similarTickets = similarTickets;
	}

	public String getTicketID()
	{
		return ticketID;
	}

	public void setTicketID(String ticketID)
	{
		this.ticketID = ticketID;
	}

}
