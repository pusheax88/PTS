package pts.dao.ticket;

import java.util.Collection;

import pts.model.ticket.Action;
import pts.model.ticket.Problem;

public interface ProblemDAO
{
	public Problem getProblem(Long problemID);
	public Collection<Problem> getProblemsForTicket(Long ticketID);
	public Long getTicketID(Long problemID);
	public void saveProblem(Problem p);
	public void saveProblems(Collection<Problem> p);
	public void addAction(Long problemID, Action... a);
	public void deleteProblem(Long problemID);
}
