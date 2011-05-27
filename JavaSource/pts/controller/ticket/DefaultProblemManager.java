package pts.controller.ticket;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;

import pts.dao.ticket.ProblemDAO;
import pts.model.network.NetworkElement;
import pts.model.ticket.Action;
import pts.model.ticket.Problem;

@ManagedBean(name="problemManager")
@ApplicationScoped
public class DefaultProblemManager implements ProblemManager
{
	private static Logger log = Logger.getLogger(DefaultProblemManager.class);

	@ManagedProperty(value="#{problemDAO}")
	private ProblemDAO problemDAO;
	
	public void setProblemDAO(ProblemDAO problemDAO)
	{
		this.problemDAO = problemDAO;
	}
	
	@Override
	public Collection<Problem> getProblemsForTicket(Long ticketID)
	{
		log.debug("Getting problems for ticket " + ticketID);
		return problemDAO.getProblemsForTicket(ticketID);
	}

	@Override
	public Problem getProblem(Long problemID)
	{
		return problemDAO.getProblem(problemID);
	}

	@Override
	public void saveProblem(Problem problem)
	{
		log.debug("Saving problem: \n " + problem);
		problemDAO.saveProblem(problem);
	}

	@Override
	public void saveProblems(Collection<Problem> p)
	{
		if(p == null)
		{
			log.debug("problem collection is null");
			return;
		}
		
		for (Iterator<Problem> iterator = p.iterator(); iterator.hasNext();)
		{
			saveProblem(iterator.next());
		}
	}

	@Override
	public Long getTicketID(Long problemID)
	{
		return problemDAO.getTicketID(problemID);
	}

	@Override
	public void addAction(Long problemID, Action... a)
	{
		problemDAO.addAction(problemID, a);
	}

	@Override
	public void deleteProblem(Long problemID)
	{
		if(problemID == null)
		{
			log.debug("Cannot delete problem - null");
			return;
		}
		log.debug("Deleting problem " + problemID);
		
		problemDAO.deleteProblem(problemID);
	}

	@Override
	public void addAne(Long problemID, NetworkElement... ne)
	{
		problemDAO.addAne(problemID, ne);
	}

	@Override
	public Collection<NetworkElement> getAffectedNetworkElements(Long problemID)
	{
		if(problemID == null)
		{
			return Collections.emptyList();
		}
		Problem p = getProblem(problemID);
		if(p == null)
		{
			return Collections.emptyList();
		}
		return p.getAffectedNetworkElements();
	}

}
