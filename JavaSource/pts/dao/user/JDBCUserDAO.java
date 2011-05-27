package pts.dao.user;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import pts.model.user.User;

@ManagedBean(name="userDAO")
@ApplicationScoped
public class JDBCUserDAO implements UserDAO
{
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public User getUser(String name, String password)
	{
		List<User> users = hibernateTemplate.find("from User u where u.name = ? and u.password = ?", 
					name, password);
		if(!users.isEmpty())
		{
			return users.iterator().next();
		}
		return null;
	}

	public Collection<User> getAllUsers()
	{
		List<User> users = hibernateTemplate.find("from User");
		return users;
	}

	public User getUser(Long id)
	{
		List<User> users = hibernateTemplate.find("from User u where u.id = ?", id);
		if(!users.isEmpty())
		{
			return users.iterator().next();
		}
		return null;
	}

}
