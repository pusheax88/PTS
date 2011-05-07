package pts.controller.ticket;

import java.util.Collection;

import pts.model.ticket.Action;

public interface ActionManager
{
	public static final String BEAN_NAME = "actionManager";
	
	public Collection<Action> getActionsForProblem(Long  problemID);
	public Long getProblemID(Long actionID);
	public Action getAction(Long actionID);
	public void saveAction(Action a);
	public void saveActions(Collection<Action> a);
	public void deleteAction(Long... actionID);
}
