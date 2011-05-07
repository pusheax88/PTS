package pts.dao.ticket;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.CollectionUtils;

import pts.model.ticket.Action;
import pts.model.ticket.Problem;

@ManagedBean(name="problemDAO")
@ApplicationScoped
public class JDBCProblemDAO implements ProblemDAO
{
	private static Logger log = Logger.getLogger(JDBCProblemDAO.class);
	
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	@ManagedProperty(value="#{actionDAO}")
	private ActionDAO actionDAO;
	
	private HibernateTemplate hibernateTemplate;
	
	public void setActionDAO(ActionDAO actionDAO)
	{
		this.actionDAO = actionDAO;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public Problem getProblem(Long problemID)
	{
		List<Problem> problems = hibernateTemplate.find(
				"from Problem as p left join fetch p.actions " +
				"where p.id = ?", problemID);
		if(!problems.isEmpty())
		{
			return problems.iterator().next();
		}
		return null;
	}

	@Override
	public Collection<Problem> getProblemsForTicket(Long ticketID)
	{
		List<Problem> problems = hibernateTemplate.find("select t.problems from Ticket t where t.id = ?", ticketID);
		return problems;
	}

	@Override
	public void saveProblem(Problem p)
	{
		if(!CollectionUtils.isEmpty(p.getActions()))
		{
			actionDAO.saveActions(p.getActions());
		}
		hibernateTemplate.saveOrUpdate(p);
	}

	@Override
	public void saveProblems(Collection<Problem> problems)
	{
		if(problems == null)
		{
			log.debug("Nothing to save - passed null collection problems");
			return;
		}
		for (Iterator<Problem> iterator = problems.iterator(); iterator.hasNext();)
		{
			saveProblem(iterator.next());
		}
	}

	@Override
	public Long getTicketID(Long problemID)
	{
		if(problemID == null)
		{
			log.debug("Passed null problemID");
			return null;
		}
		
		List res = hibernateTemplate.find(
				"select t.id " +
				"from Ticket as t join t.problems as p " +
				"where p.id = ?", problemID);
		
		log.debug("Resulting list = " + res);
		
		if(CollectionUtils.isEmpty(res))
		{
			log.debug("Resulting list is empty ");
			return null;
		}
		
		return (Long)res.get(0);
	}

	@Override
	public void addAction(Long problemID, Action... a)
	{
		Problem p = getProblem(problemID);
		p.addAction(a);
		saveProblem(p);
	}

	@Override
	public void deleteProblem(Long problemID)
	{
		hibernateTemplate.delete(
				getProblem(problemID));
	}

}
