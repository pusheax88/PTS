package pts.model.ticket;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import pts.model.user.User;

/**
 * That which can be done to solve the problem.
 * 
 */
@Entity
@Table(name = "ACTION")
public class Action
{
	@Id
	@GeneratedValue
	@Column(name = "ACTION_ID")
	private Long id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ACTION_DATE")
	private Date actionDate;
	
	@OneToOne
	@JoinColumn(name = "ACTOR_ID", referencedColumnName="USER_ID")
	private User actor;

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}

	public Date getActionDate()
	{
		return actionDate;
	}
	
	public void setActionDate(Date actionDate)
	{
		this.actionDate = actionDate;
	}

	public User getActor()
	{
		return actor;
	}
	
	public void setActor(User actor)
	{
		this.actor = actor;
	}
}
