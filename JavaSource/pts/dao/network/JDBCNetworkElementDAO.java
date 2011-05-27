package pts.dao.network;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.CollectionUtils;

import pts.core.util.BeanFinder;
import pts.model.network.Device;
import pts.model.network.NetworkElement;
import pts.model.network.properties.SnmpPropertyDefinition;

@ManagedBean(name="networkElementDAO")
@ApplicationScoped
public class JDBCNetworkElementDAO implements NetworkElementDAO
{
	private static Logger log = Logger.getLogger(JDBCNetworkElementDAO.class);
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	@ManagedProperty(value="#{propertyDefDAO}")
	private PropertyDefDAO propertyDefDAO;
	
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void setPropertyDefDAO(PropertyDefDAO propertyDefDAO)
	{
		this.propertyDefDAO = propertyDefDAO;
	}

	@Override
	public NetworkElement getNetworkElement(Long id)
	{
		Collection<NetworkElement> list = hibernateTemplate.find("from NetworkElement ne where ne.id = ?", id);
		if(!list.isEmpty())
		{
			return list.iterator().next();
		}
		return null;
	}
	
	@Override
	public Collection<NetworkElement> getNetworkElements(Long... ids)
	{
		return hibernateTemplate.find("from NetworkElement ne where ne.id = ?", 
				Arrays.asList(ids));
	}

	@Override
	public void saveNetworkElement(NetworkElement ne)
	{
		Validate.notNull(ne, "Null argument");
		log.debug("Saving network element " + ne);
		
		//saveLinkedProperties(ne);
		hibernateTemplate.saveOrUpdate(ne);
	}

	@Override
	public void saveNetworkElements(NetworkElement... ne)
	{
		Validate.notNull(ne, "Null argument");
		log.debug("Saving network elements " + Arrays.asList(ne));
		
		//saveLinkedProperties(ne);
		hibernateTemplate.saveOrUpdateAll(Arrays.asList(ne));
	}
	
	@Override
	public void deleteNetworkElements(NetworkElement... ne)
	{
		Validate.notNull(ne, "Null argument");
		log.debug("Deleting network elements " + Arrays.asList(ne));
		
		hibernateTemplate.deleteAll(Arrays.asList(ne));
	}
	
	@Deprecated
	private void saveLinkedProperties(NetworkElement... networkElements)
	{
		Collection<SnmpPropertyDefinition> props = new ArrayList<SnmpPropertyDefinition>();
		
		for (int i = 0; i < networkElements.length; i++)
		{
			props.addAll(networkElements[i].getProperties());
		}
		
		if(!CollectionUtils.isEmpty(props))
		{
			propertyDefDAO.savePropertyDefinitions(props.toArray(new SnmpPropertyDefinition[props.size()]));
		}
	}

	@Override
	public Collection<? extends NetworkElement> getConnectedToForNetworkElement(Long id)
	{
		Collection<Device> res = hibernateTemplate.find(
				"select d2 \n" +
				"from \n" +
				"	Device d1 inner join d1.interfaces i inner join i.connectedTo cp, \n" +
				"	Device d2 inner join d2.interfaces i2 \n" +
				"where \n" +
				"	d1.id = ? \n" +
				"	and i2.id = cp.id", id);
		
		log.debug("res = " + res);
		for(Device dev : res)
		{
			log.debug("dev: " + dev);
		}
		
		return res;
	}

	@Override
	public int getNumOfTickets(Long id)
	{
		BigInteger num = null;
		Session sess = BeanFinder.findBean("sessionFactory", SessionFactory.class).openSession();
		Transaction tx = null;
		try
		{
			tx = sess.beginTransaction();
			
			num = (BigInteger) sess.createSQLQuery(
					"select count(1) \n" +
					"from NETWORK_ELEMENT e, \n" +
					"TICKET t join TICKET_TO_PROBLEM ttp on t.ticket_id = ttp.ticket_id join " +
					"PROBLEM_TO_NETWORK_ELEMENT ptn on ptn.problem_id = ttp.problem_id\n" +
					"where \n" +
					"	e.NETWORK_ELEMENT_ID = :id \n" +
					"	and ptn.network_element_id = e.NETWORK_ELEMENT_ID")
					.setParameter("id", id).uniqueResult();
			
			tx.commit();
		} 
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException("Exception in getNumOfTickets", e);
		} 
		finally
		{
			sess.close();
		}
		return num != null ? Integer.valueOf(num.toString()) : 0;
	}

	@Override
	public Collection<NetworkElement> getAllNetworkElements()
	{
		return hibernateTemplate.find("from NetworkElement ne");
	}

}
