package pts.controller.user;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import pts.dao.user.UserDAO;
import pts.model.user.User;

@ManagedBean(name="userManager")
@ApplicationScoped
public class DefaultUserManager implements UserManager
{
	@ManagedProperty(value="#{userDAO}")
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	public User getUser(String name, String password)
	{
		return userDAO.getUser(name, password);
	}
	
	public Collection<User> getAllUsers()
	{
		return userDAO.getAllUsers();
	}

	public User getUser(Long id)
	{
		return userDAO.getUser(id);
	}
}
