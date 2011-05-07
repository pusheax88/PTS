package pts.controller.ticket;

import java.util.Collection;

import pts.model.ticket.Action;
import pts.model.ticket.Problem;

public interface ProblemManager
{
	public static final String BEAN_NAME = "problemManager";
	
	public Collection<Problem> getProblemsForTicket(Long ticketID);
	public Problem getProblem(Long problemID);
	public Long getTicketID(Long problemID);
	public void saveProblem(Problem p);
	public void saveProblems(Collection<Problem> p);
	public void addAction(Long valueOf, Action... a);
	public void deleteProblem(Long problemID);
}
