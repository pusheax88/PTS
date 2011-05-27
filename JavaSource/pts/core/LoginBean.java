package pts.core;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import pts.controller.user.UserManager;
import pts.model.user.User;

/**
 * Bean used to bind user to current session.
 * Also provides role checks.
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean
{
	public static final String BEAN_NAME = "loginBean";

	public static LoginBean getInstance()
	{
		return (LoginBean)FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().get(BEAN_NAME);
	}
	
	@ManagedProperty(value="#{userManager}")
	private UserManager userManager;
	private User user;
	
	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}
	
	/**
	 * Gets user for current session.
	 */
	public User getUser()
	{
		return user;
	}
	
	/**
	 * Set to null to expire session.
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
	
	/**
	 * Role check.
	 * 
	 */
	public boolean isUserInRole(String role)
	{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		
		return context.isUserInRole(role);
	}
	
	public String logout()
	{
		user = null;
		
		return "logout";
	}
	
	public boolean login(String userName, String password)
	{
		user = userManager.getUser(userName, password);
		return user != null;
	}
	
	public boolean isUserLoggedIn()
	{
		return user != null;
	}
	
}
