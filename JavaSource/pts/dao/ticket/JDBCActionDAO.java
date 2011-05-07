package pts.dao.ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import pts.model.ticket.Action;

@ManagedBean(name="actionDAO")
@ApplicationScoped
public class JDBCActionDAO implements ActionDAO
{
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public Action getAction(Long actionID)
	{
		List<Action> actions = hibernateTemplate.find("from Action a where a.id = ?", actionID);
		if(!actions.isEmpty())
		{
			return actions.iterator().next();
		}
		return null;
	}

	@Override
	public Collection<Action> getActionsForProblem(Long problemID)
	{
		List<Action> actions = hibernateTemplate.find("select p.actions from Problem p where p.id = ?", problemID);
		return actions;
	}

	@Override
	public void saveAction(Action action)
	{
		hibernateTemplate.saveOrUpdate(action);
	}

	@Override
	public void saveActions(Collection<Action> actions)
	{
		for (Iterator<Action> iterator = actions.iterator(); iterator.hasNext();)
		{
			saveAction(iterator.next());
		}
	}

	@Override
	public void deleteAction(Long... actionID)
	{
		hibernateTemplate.deleteAll(
				getActions(actionID));
	}

	@Override
	public Collection<Action> getActions(Long... actionID)
	{
		Collection<Action> res = new ArrayList<Action>();
		for (int i = 0; i < actionID.length; i++)
		{
			res.add(
					getAction(actionID[i]));
			
		}
		return res;
	}

	@Override
	public Long getProblemID(Long actionID)
	{
		List res = hibernateTemplate.find("select p.id from Problem as p join p.actions as a where a.id=?", actionID);
		return (Long)res.get(0);
	}

}
