package pts.dao.network;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.CollectionUtils;

import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyHistory;

@ManagedBean(name="propertyHistoryDAO")
@ApplicationScoped
public class JDBCPropertyHistoryDAO implements PropertyHistoryDAO
{
	private static Logger log = Logger.getLogger(JDBCPropertyHistoryDAO.class);
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public PropertyHistory getPropertyHistoryForNetworkElement(Long id)
	{
		Collection<PropertyHistory> res = hibernateTemplate.find(
				"from PropertyHistory ph where ph.networkElement.id = ?", id);
		
		if(!CollectionUtils.isEmpty(res))
		{
			return res.iterator().next();
		}
		return null;
	}

	@Override
	public void addPropertyValueToHistory(NetworkElement ne, DatedPropertyValue... props)
	{
		PropertyHistory ph = getPropertyHistoryForNetworkElement(ne.getId());
		if(ph == null)
		{
			ph = new PropertyHistory();
			ph.setNetworkElement(ne);
			ph.addPropertyValue(props);
		}
		else
		{
			ph.addPropertyValue(props);
		}
		hibernateTemplate.saveOrUpdate(ph);
	}

	@Override
	public void removePropertyValueFromHistory(NetworkElement ne, DatedPropertyValue... props)
	{
		PropertyHistory ph = getPropertyHistoryForNetworkElement(ne.getId());
		if(ph == null)
		{
			log.debug("PropertyHistory for NetworkElement " + ne + " was not found.");
			return;
		}
		else
		{
			ph.removePropertyValue(props);
		}
		hibernateTemplate.saveOrUpdate(ph);
	}

	@Override
	public void clearPropertyHistory(NetworkElement ne)
	{
		PropertyHistory ph = getPropertyHistoryForNetworkElement(ne.getId());
		if(ph == null)
		{
			log.debug("PropertyHistory for NetworkElement " + ne + " was not found.");
			return;
		}
		else
		{
			ph.getProperties().clear();
		}
		hibernateTemplate.saveOrUpdate(ph);
	}

}
