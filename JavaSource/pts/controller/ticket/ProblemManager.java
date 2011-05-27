package pts.controller.ticket;

import java.util.Collection;

import pts.model.network.NetworkElement;
import pts.model.ticket.Action;
import pts.model.ticket.Problem;

public interface ProblemManager
{
	public static final String BEAN_NAME = "problemManager";
	
	public Collection<Problem> getProblemsForTicket(Long ticketID);
	public Problem getProblem(Long problemID);
	public Long getTicketID(Long problemID);
	public Collection<NetworkElement> getAffectedNetworkElements(Long problemID);
	public void saveProblem(Problem p);
	public void saveProblems(Collection<Problem> p);
	public void addAction(Long problemID, Action... a);
	public void addAne(Long problemID, NetworkElement... ne);
	public void deleteProblem(Long problemID);
}
