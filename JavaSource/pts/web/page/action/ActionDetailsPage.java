package pts.web.page.action;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import pts.controller.ticket.ActionManager;
import pts.core.util.JsfTool;
import pts.model.ticket.Action;

@ManagedBean(name="actionDetailsPage")
@ViewScoped
public class ActionDetailsPage
{
	private static Logger log = Logger.getLogger(ActionDetailsPage.class);
	
	@ManagedProperty(value="#{actionManager}")
	private ActionManager actionManager;
	
	private Action action;
	private String actionID;
	private Long problemID;
	private String actionDescription;
	private boolean readonly = true;
	HtmlCommandButton submitButton = new HtmlCommandButton();
	
	public ActionDetailsPage()
	{
		actionID = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("actionID");
	}
	
	@PostConstruct
	public void resolveAction()
	{
		//Resolve Problem
		log.debug("Resolving action for actionID = " + actionID);
		action = actionManager.getAction(Long.valueOf(actionID));
		log.debug("Action was resolved: " + action);
		
		actionDescription = action.getDescription();
		setReadonly(true);
	}

	public String getActionID()
	{
		return actionID;
	}

	public void setActionID(String actionID)
	{
		log.debug("Set actionID=" + actionID);
		this.actionID = actionID;
	}

	public Long getProblemID()
	{
		if(problemID == null)
		{
			problemID = actionManager.getProblemID(Long.valueOf(actionID));
		}
		return problemID;
	}

	public String getActionDescription()
	{
		return actionDescription;
	}

	public void setActionDescription(String actionDescription)
	{
		log.debug("Set actionDescription=" + actionDescription);
		this.actionDescription = actionDescription;
	}

	public Action getAction()
	{
		if(action == null)
		{
			log.debug("Resolving action as it was null");
			resolveAction();
		}
		return action;
	}
	
	public void setAction(Action action)
	{
		log.debug("Set action=" + action);
		this.action = action;
	}

	public boolean getReadonly()
	{
		return readonly;
	}

	public void setReadonly(boolean readonly)
	{
		log.debug("Set readonly=" + readonly);
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

	public void setActionManager(ActionManager actionManager)
	{
		this.actionManager = actionManager;
	}
	
	//Navigation
	public String editActionAction()
	{
		log.debug("Call editActionAction");
		setReadonly(false);
		return null;
	}
	
	public String saveActionAction()
	{
		log.debug("Call saveActionAction");
		action = getAction();
		action.setDescription(
					ObjectUtils.toString(actionDescription));
		
		actionManager.saveAction(action);

		setReadonly(true);
		return null;
	}
	
	public void deleteActionAction()
	{
		log.debug("Call deleteActionAction\n actionID = " + actionID);
		
		Long problemID = actionManager.getProblemID(Long.valueOf(actionID));
		
		actionManager.deleteAction(Long.valueOf(actionID));
		
		JsfTool.redirect("/pages/problem_details.jsf?problemID=" + problemID);
	}
	
	public String viewActionAction()
	{
		log.debug("Call viewActionAction");
		setReadonly(true);
		return null;
	}
}
