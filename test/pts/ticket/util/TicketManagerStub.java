package pts.ticket.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ManagedBean;

import pts.controller.ticket.TicketManager;
import pts.model.ticket.Ticket;

@ManagedBean
public class TicketManagerStub implements TicketManager
{
	private Collection<Ticket> tickets = new ArrayList<Ticket>();

	@Override
	public Collection<Ticket> getTickets()
	{
		Ticket t = new Ticket();
		t.setId(1L);
		t.setDescription("Description of first ticket");
		
		Ticket t2 = new Ticket();
		t2.setId(2L);
		t2.setDescription("Description of second ticket");
		
		Ticket t3 = new Ticket();
		t3.setId(3L);
		t3.setDescription("Description of third ticket");
		
		Ticket t4 = new Ticket();
		t4.setId(4L);
		t4.setDescription("Description of fourth ticket");
		return Arrays.asList(t, t2, t3, t4);
	}

	@Override
	public void saveTicket(Ticket t)
	{
		tickets.add(t);
	}

	@Override
	public Ticket getTicket(Long ticketID)
	{
		Ticket t4 = new Ticket();
		t4.setId(4L);
		t4.setDescription("Description of fourth ticket");
		
		return t4;
	}

}
