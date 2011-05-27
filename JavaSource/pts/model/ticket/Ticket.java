/**
 * The model to define ticket tracking system.
 */
package pts.model.ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.model.user.User;

/**
 * The unit which represents a group of related network problems.
 * 
 */
@Entity
@Table(name = "TICKET")
public class Ticket
{
	private static Logger log = Logger.getLogger(Ticket.class);
	
	@Id
	@GeneratedValue
	@Column(name = "TICKET_ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "TICKET_DATE")
	private Date ticketDate;
	
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="STATUS_ID", referencedColumnName="STATUS_ID")
	private TicketStatus status;
	
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="ASSIGNED_TO", referencedColumnName="USER_ID")
	private User assignedTo;
	
	@OneToMany
	@Cascade({CascadeType.ALL})
	@JoinTable(
            name="TICKET_TO_PROBLEM",
            joinColumns = @JoinColumn( name="TICKET_ID"),
            inverseJoinColumns = @JoinColumn( name="PROBLEM_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Problem> problems;
	
	@Override
	public String toString()
	{
		return "ticket{" + id + ";" + description + ";" + status + ";" + assignedTo + "}";
	}
	
//	public String toLogString()
//	{
//		StringBuilder sb = new StringBuilder();
//		sb.append(id).append(":")
//			.append(description).append(":")
//			.append(status).append(":")
//			.append(assignedTo);
//		return sb.toString();
//	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long ticketID)
	{
		this.id = ticketID;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public TicketStatus getStatus()
	{
		return status;
	}
	
	public void setStatus(TicketStatus status)
	{
		this.status = status;
	}
	
	public User getAssignedTo()
	{
		return assignedTo;
	}
	
	public void setAssignedTo(User assignedTo)
	{
		this.assignedTo = assignedTo;
	}

	public Collection<Problem> getProblems()
	{
		return problems;
	}

	public void setProblems(Collection<Problem> problems)
	{
		this.problems = problems;
	}
	
	public void addProblems(Problem... problem)
	{
		if(problem == null || problem.length == 0)
		{
			log.debug("Cannot add problem - null or zero sized array passed.");
			return;
		}
		if(problems == null)
		{
			problems = new ArrayList<Problem>();
		}
		for(int i = 0; i < problem.length; i++)
		{
			log.debug("Ticket " + id + "; Problem added: " + problem[i]);
			problems.add(problem[i]);
		}
	}

	public Date getTicketDate()
	{
		return ticketDate;
	}

	public void setTicketDate(Date ticketDate)
	{
		this.ticketDate = ticketDate;
	}
	
}
