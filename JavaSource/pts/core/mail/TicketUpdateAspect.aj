package pts.core.mail;

import org.apache.log4j.Logger;

import pts.model.ticket.Ticket;
import pts.controller.ticket.*;

public aspect TicketUpdateAspect
{

	private static Logger log = Logger.getLogger(TicketUpdateAspect.class);
	
	pointcut updateTicketOperation():
		execution( public * TicketManager.saveTicket(..) );
	
	pointcut updateTicketOperationWithArgs(Ticket t):
		updateTicketOperation() && args(t);
	
	after(Ticket t):
		updateTicketOperationWithArgs(t)
		{
			notifyTicket(t);
			logNotification();
		}
	
	private void logNotification()
	{
		log.debug("Mail notification complete.");
	}
	
	private void notifyTicket(Ticket t)
	{
		//TODO notification stub
	}
}
