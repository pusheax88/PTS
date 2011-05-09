package pts.model.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import pts.model.network.NetworkElement;

/**
 * Distinguishable problem for ticket.
 * 
 */
@Entity
@Table(name = "PROBLEM")
public class Problem
{
	private static Logger log = Logger.getLogger(Problem.class);
	
	@Id
	@GeneratedValue
	@Column(name = "PROBLEM_ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "FROM_DATE")
	private Date fromDate;
	
	@Column(name = "TO_DATE")
	private Date toDate;
	
	@OneToMany(cascade={CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(
            name="PROBLEM_TO_ACTION",
            joinColumns = @JoinColumn( name="PROBLEM_ID"),
            inverseJoinColumns = @JoinColumn( name="ACTION_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Action> actions;
	
	@OneToMany(cascade={CascadeType.ALL})
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	@JoinTable(
            name="PROBLEM_TO_NETWORK_ELEMENT",
            joinColumns = @JoinColumn( name="PROBLEM_ID"),
            inverseJoinColumns = @JoinColumn( name="NETWORK_ELEMENT_ID")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
	private Collection<NetworkElement> affectedNetworkElements;

	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
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

	public Date getFromDate()
	{
		return fromDate;
	}
	
	public void setFromDate(Date fromDate)
	{
		this.fromDate = fromDate;
	}

	public Date getToDate()
	{
		return toDate;
	}

	public void setToDate(Date toDate)
	{
		this.toDate = toDate;
	}
	
	public Collection<Action> getActions()
	{
		return actions;
	}

	public void setActions(Collection<Action> actions)
	{
		this.actions = actions;
	}
	
	public void addAction(Action... action)
	{
		if(action == null || action.length == 0)
		{
			log.debug("Null argument action - returning");
			return;
		}
		if(actions == null)
		{
			actions = new ArrayList<Action>();
		}
		actions.addAll(Arrays.asList(action));
	}

	public Collection<NetworkElement> getAffectedNetworkElements()
	{
		return affectedNetworkElements;
	}

	public void setAffectedNetworkElements(Collection<NetworkElement> affectedNetworkElements)
	{
		this.affectedNetworkElements = affectedNetworkElements;
	}
	
	public void addAffectedNetworkElements(NetworkElement... element)
	{
		if(element == null || element.length == 0)
		{
			log.debug("Empty argument element - returning");
			return;
		}
		if(affectedNetworkElements == null)
		{
			affectedNetworkElements = new ArrayList<NetworkElement>();
		}
		affectedNetworkElements.addAll(Arrays.asList(element));
	}
}
