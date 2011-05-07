package pts.model.ticket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "TICKET_STATUS")
public class TicketStatus
{
	private static Logger log = Logger.getLogger(TicketStatus.class);
	public static final TicketStatus OPEN = new TicketStatus(1L, "Open");
	public static final TicketStatus IN_PROGRESS = new TicketStatus(2L, "In Progress");
	public static final TicketStatus VERIFIED = new TicketStatus(3L, "Verified");
	public static final TicketStatus CLOSED = new TicketStatus(4L, "Closed");
	public static final TicketStatus REOPENED = new TicketStatus(5L, "Reopened");
	
	private static final List<TicketStatus> ticketStatuses = new ArrayList<TicketStatus>(){{
		add(OPEN);add(IN_PROGRESS);add(VERIFIED);add(CLOSED);add(REOPENED);}};
	
	@Id
	@Column(name="STATUS_ID")
	private Long id;
	
	@Column(name="STATUS_NAME")
	private String statusName;
	
	@Override
	public String toString()
	{
		return statusName;
	}
	
	public TicketStatus()
	{
	}
	
	public TicketStatus(Long id, String statusName)
	{
		this.id = id;
		this.statusName = statusName;
	}
	
	public static TicketStatus resolveByID(Long id)
	{
		log.debug("Resolving ticketStatus for id = " + id);
		for (Iterator<TicketStatus> iterator = ticketStatuses.iterator(); iterator.hasNext();)
		{
			TicketStatus ts = iterator.next();
			if(ts.getId().equals(id))
			{
				return ts;
			}
		}
		log.debug("Could not resolve ticketStatus for id = " + id);
		return null;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getStatusName()
	{
		return statusName;
	}
	
	public void setStatusName(String statusName)
	{
		this.statusName = statusName;
	}

}
