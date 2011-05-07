package pts.controller.ticket;

import java.util.Collection;
import java.util.Iterator;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;

import pts.dao.ticket.ActionDAO;
import pts.model.ticket.Action;

@ManagedBean(name="actionManager")
@ApplicationScoped
public class DefaultActionManager implements ActionManager
{
	private static Logger log = Logger.getLogger(DefaultActionManager.class);

	@ManagedProperty(value="#{actionDAO}")
	private ActionDAO actionDAO;
	
	public void setActionDAO(ActionDAO actionDAO)
	{
		this.actionDAO = actionDAO;
	}

	@Override
	public Collection<Action> getActionsForProblem(Long problemID)
	{
		return actionDAO.getActionsForProblem(problemID);
	}

	@Override
	public Action getAction(Long actionID)
	{
		return actionDAO.getAction(actionID);
	}

	@Override
	public void saveAction(Action a)
	{
		log.debug("Saving action " + a);
		actionDAO.saveAction(a);
	}

	@Override
	public void saveActions(Collection<Action> a)
	{
		if(a == null)
		{
			log.debug("Cannot save null actions");
		}
		
		for (Iterator<Action> iterator = a.iterator(); iterator.hasNext();)
		{
			saveAction(iterator.next());
		}
	}

	@Override
	public void deleteAction(Long... actionID)
	{
		if(actionID == null || actionID.length == 0)
		{
			log.debug("Cannot delete Action(s) - empty input");
		}
		log.debug("Deleting Action(s) " + actionID);
		actionDAO.deleteAction(actionID);
	}

	@Override
	public Long getProblemID(Long actionID)
	{
		if(actionID == null)
		{
			log.debug("Return null - empty input.");
		}
		log.debug("Resolving problemID for actionID=" + actionID);
		
		return actionDAO.getProblemID(actionID);
	}
	
}
