package pts.web.page.problem;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import pts.controller.ticket.TicketManager;
import pts.core.util.BeanFinder;
import pts.core.util.JsfTool;
import pts.model.ticket.Problem;

@ManagedBean(name="problemCreationPage")
@RequestScoped
public class ProblemCreationPage
{
	private static Logger log = Logger.getLogger(ProblemCreationPage.class);
	
	public String ticketID;

	public String problemName;
	public String problemDescription;
	private Date problemFromDate;
	private Date problemToDate;
	
	public ProblemCreationPage()
	{
		log.debug("Creating instance of ProblemCreationPage");
		ticketID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ticketID");
	}
	
	public String getTicketID()
	{
		return ticketID;
	}
	
	public void setTicketID(String ticketID)
	{
		log.debug("Set ticketID = " + ticketID);
		this.ticketID = ticketID;
	}
	
	public String getProblemName()
	{
		return problemName;
	}

	public void setProblemName(String problemName)
	{
		log.debug("Set problemName = " + problemName);
		this.problemName = problemName;
	}

	public String getProblemDescription()
	{
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription)
	{
		log.debug("Set problemDescription = " + problemDescription);
		this.problemDescription = problemDescription;
	}

	public Date getProblemFromDate()
	{
		return problemFromDate;
	}

	public void setProblemFromDate(Date problemFromDate)
	{
		log.debug("Set problemFromDate = " + problemFromDate);
		this.problemFromDate = problemFromDate;
	}

	public Date getProblemToDate()
	{
		return problemToDate;
	}

	public void setProblemToDate(Date problemToDate)
	{
		log.debug("Set problemToDate = " + problemToDate);
		this.problemToDate = problemToDate;
	}

	//Navigation
	public void submitProblemAction()
	{
		TicketManager ticketManager = BeanFinder.findBean("ticketManager", TicketManager.class);
		log.debug("Saving problem for ticket " + ticketID + " with fields\n" + 
				problemName + "; " + problemDescription + "; " + problemFromDate + "; " + problemToDate);
		
		Problem p = new Problem();
		p.setName(problemName);
		p.setDescription(problemDescription);
		p.setFromDate(problemFromDate);
		p.setToDate(problemToDate);
		ticketManager.addProblem(
				Long.valueOf(ticketID), 
				p);
		
		JsfTool.redirect("/pages/ticket_details.jsf?ticketID=" + ticketID);
	}
}
