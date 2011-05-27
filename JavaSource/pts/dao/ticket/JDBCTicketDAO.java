package pts.dao.ticket;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import pts.model.ticket.Problem;
import pts.model.ticket.Ticket;

@ManagedBean(name="ticketDAO")
@ApplicationScoped
public class JDBCTicketDAO implements TicketDAO
{
	private static Logger log = Logger.getLogger(JDBCTicketDAO.class);
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	@ManagedProperty(value="#{problemDAO}")
	private ProblemDAO problemDAO;
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void setProblemDAO(ProblemDAO problemDAO)
	{
		this.problemDAO = problemDAO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Ticket> getTickets()
	{
		List<Ticket> tickets = hibernateTemplate.find("from Ticket");
		return tickets;
	}

	@Override
	public void saveTicket(Ticket t)
	{
//		if(t.getStatus() != null)
//		{
//			hibernateTemplate.saveOrUpdate(t.getStatus());
//		}
//		
//		if(t.getAssignedTo() != null)
//		{
//			hibernateTemplate.saveOrUpdate(t.getAssignedTo());
//		}
//		
//		if(t.getProblems() != null)
//		{
//			problemDAO.saveProblems(t.getProblems());
//		}
		
		hibernateTemplate.saveOrUpdate(t);
	}

	@Override
	public Ticket getTicket(Long id)
	{
		List<Ticket> tickets = hibernateTemplate.find(
				"from Ticket as t " +
				"left join fetch t.problems " + 
				"where t.id = ?", id);
		if(!tickets.isEmpty())
		{
			return tickets.iterator().next();
		}
		return null;
	}

	@Override
	public void addProblem(Long ticketID, Problem... p)
	{
		Ticket t = getTicket(ticketID);
		t.addProblems(p);
		saveTicket(t);
	}

	@Override
	public void deleteTicket(Long ticketID)
	{
		if(ticketID == null)
		{
			log.debug("Delete ticket - passed null ticketID");
			return;
		}
		log.debug("Deleting ticket " + ticketID);
		hibernateTemplate.delete(
				getTicket(ticketID));
	}

}
