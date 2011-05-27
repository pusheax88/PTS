package pts.dao.network;

import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import pts.model.network.properties.PropertyValue;

@ManagedBean(name="propertyValueDAO")
@ApplicationScoped
public class JDBCPropertyValueDAO implements PropertyValueDAO
{
	private static Logger log = Logger.getLogger(JDBCPropertyValueDAO.class);
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public PropertyValue getPropertyValue(Long id)
	{
		Collection<PropertyValue> list = hibernateTemplate.find("from PropertyValue p where p.id = ?", id);
		if(!list.isEmpty())
		{
			return list.iterator().next();
		}
		return null;
	}

	@Override
	public Collection<PropertyValue> getPropertyValues(Long... ids)
	{
		return (Collection<PropertyValue>)hibernateTemplate.find("from PropertyValue p where p.id = ?", Arrays.asList(ids));
	}

	@Override
	public void savePropertyValues(PropertyValue... props)
	{
		Validate.notNull(props, "Null argument");
		log.debug("Saving properties " + Arrays.asList(props));
		
		hibernateTemplate.saveOrUpdateAll(Arrays.asList(props));
	}

	@Override
	public void deletePropertyValues(PropertyValue... props)
	{
		Validate.notNull(props, "Null argument");
		log.debug("Deleting property definitions " + Arrays.asList(props));
		
		hibernateTemplate.deleteAll(Arrays.asList(props));
	}

}
