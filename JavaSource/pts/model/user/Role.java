package pts.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role
{
	public static final Role ADMIN = new Role(1L, "admin");
	public static final Role IT_SPEC = new Role(2L, "it_spec");
	public static final Role USER = new Role(3L, "user");
		
	@Id
	@Column(name = "ROLE_ID")
	private Long id;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	public Role()
	{
	}
	
	public Role(Long id, String name)
	{
		this.id = id;
		this.roleName = name;
	}
	
	public void setId(Long roleID)
	{
		this.id = roleID;
	}
	
	public Long getId()
	{
		return id;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}
	
	public String getRoleName()
	{
		return roleName;
	}
	
}
