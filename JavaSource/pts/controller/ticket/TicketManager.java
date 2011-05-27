package pts.controller.ticket;

import java.util.Collection;

import pts.model.ticket.Problem;
import pts.model.ticket.Ticket;

public interface TicketManager
{
	public static final String BEAN_NAME = "ticketManager";
	public Collection<Ticket> getTickets();
	public Ticket getTicket(Long ticketID);
	public void saveTicket(Ticket t);
	public void addProblem(Long ticketID, Problem... p);
	public void deleteTicket(Long ticketID);
	public boolean compareTickets(Ticket t1, Ticket t2);
	public Collection<Ticket> findSimilarTicket(Ticket ticket);
}
