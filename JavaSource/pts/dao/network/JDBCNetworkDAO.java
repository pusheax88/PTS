package pts.dao.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.CollectionUtils;

import pts.model.network.Network;
import pts.model.network.NetworkElement;

@ManagedBean(name="networkDAO")
@ApplicationScoped
public class JDBCNetworkDAO implements NetworkDAO
{
	private static Logger log = Logger.getLogger(JDBCNetworkDAO.class);
	@ManagedProperty(value="#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	@ManagedProperty(value="#{networkElementDAO}")
	private NetworkElementDAO networkElementDAO;
	
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	public void setNetworkElementDAO(NetworkElementDAO networkElementDAO)
	{
		this.networkElementDAO = networkElementDAO;
	}

	@Override
	public Network getNetwork(Long id)
	{
		Collection<Network> list = hibernateTemplate.find("from Network n where n.id = ?", id);
		if(!list.isEmpty())
		{
			return list.iterator().next();
		}
		return null;
	}

	@Override
	public Collection<Network> getNetworks()
	{
		return hibernateTemplate.find("from Network");
	}

	@Override
	public void saveNetwork(Network network)
	{
		Validate.notNull(network, "Null argument");
		log.debug("Saving network " + network);
		
		//saveLinkedNetworkElements(network);
		hibernateTemplate.saveOrUpdate(network);
	}
	
	@Deprecated
	private void saveLinkedNetworkElements(Network... network)
	{
		Collection<NetworkElement> neList = new ArrayList<NetworkElement>();
		
		for(int i = 0; i < network.length; i++)
		{
			neList.addAll(network[i].getNetworkElements());
		}
		
		if(!CollectionUtils.isEmpty(neList))
		{
			networkElementDAO.saveNetworkElements(neList.toArray(new NetworkElement[neList.size()]));
		}
	}

	@Override
	public void saveNetworks(Network... list)
	{
		Validate.notNull(list, "Null argument");
		log.debug("Saving networks " + Arrays.asList(list));
		
		//saveLinkedNetworkElements(list);
		hibernateTemplate.saveOrUpdateAll(Arrays.asList(list));
	}

	@Override
	public void deleteNetwork(Network network)
	{
		Validate.notNull(network, "Null argument");
		hibernateTemplate.delete(network);
	}

	@Override
	public Network getNetworkForNetworkElement(Long networkElementID)
	{
		Validate.notNull(networkElementID, "Null argument");
		
		Collection<Network> nList = hibernateTemplate.find("select n from Network n join n.networkElements ne where ne.id = ?", networkElementID);
		
		if(!CollectionUtils.isEmpty(nList))
		{
			return nList.iterator().next();
		}
		
		return null;
	}

}
