package pts.web.page.problem;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import pts.controller.ticket.ActionManager;
import pts.controller.ticket.ProblemManager;
import pts.core.util.JsfTool;
import pts.model.ticket.Problem;

@ManagedBean(name="problemDetailsPage")
@ViewScoped
public class ProblemDetailsPage
{
	private static Logger log = Logger.getLogger(ProblemDetailsPage.class);
	
	@ManagedProperty(value="#{problemManager}")
	private ProblemManager problemManager;
	
	@ManagedProperty(value="#{actionManager}")
	private ActionManager actionManager;
	
	private Problem problem;
	private String problemID;
	private String problemDescription;
	private Date problemFromDate;
	private Date problemToDate;
	private boolean readonly = true;
	HtmlCommandButton submitButton = new HtmlCommandButton();
	
	public ProblemDetailsPage()
	{
		problemID = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("problemID");
	}
	
	@PostConstruct
	public void resolveProblem()
	{
		//Resolve Problem
		log.debug("Resolving problem for problemID = " + problemID);
		problem = problemManager.getProblem(
				Long.valueOf(problemID));
		log.debug("Problem was resolved: " + problem);
		
		problemDescription = problem.getDescription();
		problemFromDate = problem.getFromDate();
		problemToDate = problem.getToDate();
		setReadonly(true);
	}
	
	public String getTicketID()
	{
		return problemManager.getTicketID(
				Long.valueOf(problemID))
				.toString();
	}

	public Problem getProblem()
	{
		if(problem == null)
		{
			log.debug("Resolving problem as it was null");
			resolveProblem();
		}
		return problem;
	}

	public void setProblem(Problem problem)
	{
		this.problem = problem;
	}

	public String getProblemDescription()
	{
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription)
	{
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

	public boolean getReadonly()
	{
		return readonly;
	}

	public void setReadonly(boolean readonly)
	{
		submitButton.setRendered(!readonly);
		this.readonly = readonly;
	}

	public HtmlCommandButton getSubmitButton()
	{
		return submitButton;
	}

	public void setSubmitButton(HtmlCommandButton submitButton)
	{
		this.submitButton = submitButton;
	}

	public void setProblemManager(ProblemManager problemManager)
	{
		this.problemManager = problemManager;
	}

	public void setActionManager(ActionManager actionManager)
	{
		this.actionManager = actionManager;
	}
	
	//Navigation
	public String editProblemAction()
	{
		log.debug("Call editProblemAction");
		setReadonly(false);
		return null;
	}
	
	public String saveProblemAction()
	{
		log.debug("Call saveProblemAction");
		problem = getProblem();
		problem.setDescription(
					ObjectUtils.toString(problemDescription));
		
		problemManager.saveProblem(problem);

		setReadonly(true);
		return null;
	}
	
	public void deleteProblemAction()
	{
		log.debug("Call deleteProblemAction\n problemID = " + problemID);
		
		Long ticketID = problemManager.getTicketID(Long.valueOf(problemID));
		
		problemManager.deleteProblem(Long.valueOf(problemID));
		
		JsfTool.redirect("/pages/ticket_details.jsf?ticketID=" + ticketID);
	}
	
	public String viewProblemAction()
	{
		log.debug("Call viewProblemAction");
		setReadonly(true);
		return null;
	}
	
	
}
