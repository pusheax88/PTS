package pts.dao.ticket;

import java.util.Collection;

import pts.model.ticket.Action;

public interface ActionDAO
{
	public Action getAction(Long actionID);
	public Long getProblemID(Long actionID);
	public Collection<Action> getActions(Long... actionID);
	public Collection<Action> getActionsForProblem(Long problemID);
	public void saveAction(Action action);
	public void saveActions(Collection<Action> actions);
	public void deleteAction(Long... actionID);
}
