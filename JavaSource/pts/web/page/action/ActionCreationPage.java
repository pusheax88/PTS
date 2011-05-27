package pts.web.page.action;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pts.controller.ticket.ProblemManager;
import pts.core.LoginBean;
import pts.core.util.BeanFinder;
import pts.core.util.JsfTool;
import pts.model.ticket.Action;

@ManagedBean(name="actionCreationPage")
@RequestScoped
public class ActionCreationPage
{
	private static Logger log = Logger.getLogger(ActionCreationPage.class);
	
	public String problemID;

	public String actionDescription;
	
	public ActionCreationPage()
	{
		log.debug("Creating instance of ActionCreationPage");
		setProblemID((String)JsfTool.getRequestParam("problemID"));
	}
	
	public String getProblemID()
	{
		if(StringUtils.isEmpty(problemID))
		{
			problemID = (String)JsfTool.getRequestParam("problemID");
		}
		return problemID;
	}
	
	public void setProblemID(String problemID)
	{
		log.debug("Set problemID = " + problemID);
		this.problemID = problemID;
	}

	public String getActionDescription()
	{
		return actionDescription;
	}

	public void setActionDescription(String actionDescription)
	{
		log.debug("Set actionDescription = " + actionDescription);
		this.actionDescription = actionDescription;
	}

	//Navigation
	public void submitActionAction()
	{
		ProblemManager problemManager = BeanFinder.findBean("problemManager", ProblemManager.class);
		log.debug("Saving action for problem " + problemID + " with fields\n" + 
				actionDescription);
		
		Action a = new Action();
		a.setDescription(actionDescription);
		a.setActionDate(new Date());
		a.setActor(LoginBean.getInstance().getUser());
		problemManager.addAction(
				Long.valueOf(problemID), 
				a);
		
		JsfTool.redirect("/pages/problem_details.jsf?problemID=" + problemID);
	}
}
