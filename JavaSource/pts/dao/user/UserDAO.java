package pts.dao.user;

import java.util.Collection;

import pts.model.user.User;

public interface UserDAO
{
	public User getUser(String name, String password);
	
	public User getUser(Long id);
	
	public Collection<User> getAllUsers();
	
}
