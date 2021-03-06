package pts.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Entity with name, password, email and role.
 * Used for means of security and for tracking users which can be assigned problems.
 */
@Entity
@Table(name = "WEB_USER")
public class User
{
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	
	@Column(name = "USER_NAME")
	private String name;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID")
	private Role role;
	
	public void setId(Long userID)
	{
		this.id = userID;
	}
	
	public Long getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public Role getRole()
	{
		return role;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}
	
	public String toString()
	{
		return "user{" + id + ";" + name + "}";
	}
}
