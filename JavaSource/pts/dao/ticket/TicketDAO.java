package pts.dao.ticket;

import java.util.Collection;

import pts.model.ticket.Problem;
import pts.model.ticket.Ticket;

public interface TicketDAO
{
	public Ticket getTicket(Long id);
	public Collection<Ticket> getTickets();
	public void saveTicket(Ticket t);
	public void addProblem(Long ticketID, Problem... p);
	public void deleteTicket(Long ticketID);
}
