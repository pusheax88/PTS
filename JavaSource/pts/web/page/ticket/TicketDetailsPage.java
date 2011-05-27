package pts.web.page.ticket;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import pts.controller.ticket.TicketManager;
import pts.controller.user.UserManager;
import pts.core.util.JsfTool;
import pts.model.ticket.Problem;
import pts.model.ticket.Ticket;
import pts.model.ticket.TicketStatus;
import pts.model.user.User;

@ManagedBean(name="ticketDetailsPage")
@ViewScoped
public class TicketDetailsPage
{
	private static Logger log = Logger.getLogger(TicketDetailsPage.class);
	public static String BEAN_NAME = "ticketDetailsPage";
	
	@ManagedProperty(value="#{ticketManager}")
	private TicketManager ticketManager;
	
	@ManagedProperty(value="#{userManager}")
	private UserManager userManager;
	
	private String ticketID;
	
	private String ticketDescription;
	private String ticketStatus;
	private String assignedToUserID;
	private Collection<Problem> problems;
	
	private SelectItem[] allUsers;
	
	private Ticket ticket;
	private boolean readonly = true;
	
	HtmlCommandButton submitButton = new HtmlCommandButton();

	public static TicketDetailsPage getInstance()
	{
		return (TicketDetailsPage)FacesContext.getCurrentInstance().
			getExternalContext().getRequestMap().get(BEAN_NAME);
	}
	
	public TicketDetailsPage()
	{
		ticketID = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("ticketID");
	}
	
	@PostConstruct
	public void resolveTicket()
	{
		if(ticketID == null)
		{
			return;
		}
		//Resolve Ticket
		log.debug("Resolving ticket for ticketID = " + ticketID);
		ticket = ticketManager.getTicket(
				Long.valueOf(ticketID));
		log.debug("Ticket was resolved: " + ticket);
		
		ticketDescription = ticket.getDescription();
		ticketStatus = ticket.getStatus().getId().toString();
		if(ticket.getAssignedTo() != null)
		{
			setAssignedToUserID(ticket.getAssignedTo().getId().toString());
		}
		else
		{
			setAssignedToUserID("0");
		}
		log.debug("Setting problems");
		problems = ticket.getProblems();
		log.debug("problems are set");
	}
	
	public TicketManager getTicketManager()
	{
		return ticketManager;
	}

	public void setTicketManager(TicketManager ticketManager)
	{
		log.debug("TicketManager initialized");
		this.ticketManager = ticketManager;
	}
	
	public void setUserManager(UserManager userManager)
	{
		log.debug("UserManager initialized");
		this.userManager = userManager;
	}
	
	public void setTicketID(String ticketID)
	{
		log.debug("Setting param ticketID " + ticketID);
		this.ticketID = ticketID;
		resolveTicket();
	}

	public String getTicketID()
	{
		return ticketID;
	}

	public void setTicket(Ticket t)
	{
		this.ticket = t;
	}

	public Ticket getTicket()
	{
		if(ticket == null)
		{
			log.debug("Get ticket. Trying to resolve ticket as it is null");
			resolveTicket();
		}
		return ticket;
	}
	
	public Collection<Problem> getProblems()
	{
		return problems;
	}
	
	public SelectItem[] getAllUsers()
	{
		if(allUsers == null)
		{
			Collection <User> allUsersCollection = userManager.getAllUsers();
			SelectItem[] res = new SelectItem[allUsersCollection.size() + 1];
			res[0] = new SelectItem("0", "");
			
			int i = 1;
			for (Iterator<User> iterator = allUsersCollection.iterator(); iterator.hasNext();)
			{
				User u = iterator.next();
				res[i] = new SelectItem(u.getId(), u.getName());
				i++;
			}
			allUsers = res;
		}
		
		return allUsers;
	}

	public void setReadonly(boolean readonly)
	{
		log.debug("Set readonly " + readonly);
		submitButton.setRendered(!readonly);
		this.readonly = readonly;
	}
	
	public boolean getReadonly()
	{
		return readonly;
	}
	
	public String getTicketDescription()
	{
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription)
	{
		log.debug("Set ticketDescription " + ticketDescription);
		this.ticketDescription = ticketDescription;
	}

	public String getTicketStatus()
	{
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus)
	{
		log.debug("Set ticketStatus " + ticketStatus);
		this.ticketStatus = ticketStatus;
	}
	
	public String getAssignedToUserID()
	{
		return assignedToUserID;
	}

	public void setAssignedToUserID(String assignedToUserID)
	{
		this.assignedToUserID = assignedToUserID;
	}

	public HtmlCommandButton getSubmitButton()
	{
		submitButton.setRendered(!getReadonly());
		return submitButton;
	}

	public void setSubmitButton(HtmlCommandButton submitButton)
	{
		log.debug("Set submitButton");
		this.submitButton = submitButton;
	}

	//Navigation
	public String editTicketAction()
	{
		log.debug("Call editTicketAction");
		setReadonly(false);
		return null;
	}
	
	public String saveTicketAction()
	{
		log.debug("Call saveTicketAction");
		ticket = getTicket();
		ticket.setDescription(
					ObjectUtils.toString(ticketDescription));
		ticket.setStatus(TicketStatus.resolveByID(Long.valueOf(ObjectUtils.toString(ticketStatus))));
		ticket.setAssignedTo(userManager.getUser(Long.valueOf(ObjectUtils.toString(assignedToUserID))));
		
		ticketManager.saveTicket(ticket);

		setReadonly(true);
		return null;
	}
	
	public String viewTicketAction()
	{
		log.debug("Call viewTicketAction");
		setReadonly(true);
		return null;
	}
	
	public void deleteTicketAction()
	{
		log.debug("Call deleteTicketAction \n" + 
				"deleting ticket " + ticketID);
		
		ticketManager.deleteTicket(
				Long.valueOf(ticketID));
		
		JsfTool.redirect("/pages/tickets.jsf");
	}

}
