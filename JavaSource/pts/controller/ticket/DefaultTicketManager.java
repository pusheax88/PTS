package pts.controller.ticket;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;

import pts.core.metric.network.TicketMetric;
import pts.core.metric.network.TicketMetricComparator;
import pts.dao.ticket.TicketDAO;
import pts.model.ticket.Problem;
import pts.model.ticket.Ticket;

@ManagedBean(name="ticketManager")
@ApplicationScoped
public class DefaultTicketManager implements TicketManager
{	
	private static Logger log = Logger.getLogger(DefaultTicketManager.class);
	
	@ManagedProperty(name="ticketDAO", value="#{ticketDAO}")
	private TicketDAO ticketDAO;

	public void setTicketDAO(TicketDAO ticketDAO)
	{
		this.ticketDAO = ticketDAO;
	}

	@Override
	public Collection<Ticket> getTickets()
	{
		return ticketDAO.getTickets();
	}
	
	@Override
	public Ticket getTicket(Long ticketID)
	{
		return ticketDAO.getTicket(ticketID);
	}

	@Override
	public void saveTicket(Ticket t)
	{
		log.debug("Saving ticket: \n" + t);
		ticketDAO.saveTicket(t);
	}

	@Override
	public void addProblem(Long ticketID, Problem... p)
	{
		ticketDAO.addProblem(ticketID, p);
	}

	@Override
	public void deleteTicket(Long ticketID)
	{
		ticketDAO.deleteTicket(ticketID);
	}

	/**
	 * @returns true - when tickets are similar. false - otherwise. 
	 */
	@Override
	public boolean compareTickets(Ticket t1, Ticket t2)
	{
		return TicketMetricComparator.getInstance().compareMetrics(new TicketMetric(t1), new TicketMetric(t2));
	}

	@Override
	public Collection<Ticket> findSimilarTicket(Ticket t1)
	{
		Collection<Ticket> result = new ArrayList<Ticket>();
		for(Ticket t2 : getTickets())
		{
			if(t2.getId().equals(t1.getId()))
			{
				continue;
			}
			if(compareTickets(t1, t2) == true)
			{
				result.add(t2);
			}
		}
		
		return result;
	}

}
