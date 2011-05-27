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

import pts.model.network.properties.PropertyDefinition;

@ManagedBean(name="propertyDefDAO")
@ApplicationScoped
public class JDBCPropertyDefDAO implements PropertyDefDAO
{
	private static Logger log = Logger.getLogger(JDBCPropertyDefDAO.class);
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Override
	public PropertyDefinition getPropertyDefinition(Long id)
	{
		Collection<PropertyDefinition> list = hibernateTemplate.find("from PropertyDefinition p where p.id = ?", id);
		if(!list.isEmpty())
		{
			return list.iterator().next();
		}
		return null;
	}

	@Override
	public Collection<PropertyDefinition> getPropertyDefinitions(Long... ids)
	{
		return (Collection<PropertyDefinition>)hibernateTemplate.find("from PropertyDefinition p where p.id = ?", Arrays.asList(ids));
	}

	@Override
	public void savePropertyDefinitions(PropertyDefinition... props)
	{
		Validate.notNull(props, "Null argument");
		log.debug("Saving property definitions " + Arrays.asList(props));
		
		hibernateTemplate.saveOrUpdateAll(Arrays.asList(props));
	}

	@Override
	public void deletePropertyDefinitions(PropertyDefinition... props)
	{
		Validate.notNull(props, "Null argument");
		log.debug("Deleting property definitions " + Arrays.asList(props));
		
		hibernateTemplate.deleteAll(Arrays.asList(props));
	}

}
