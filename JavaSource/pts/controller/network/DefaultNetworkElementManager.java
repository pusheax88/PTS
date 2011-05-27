package pts.controller.network;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import pts.dao.network.NetworkElementDAO;
import pts.model.network.NetworkElement;

@ManagedBean(name="networkElementManager")
@ApplicationScoped
public class DefaultNetworkElementManager implements NetworkElementManager
{
	private static Logger log = Logger.getLogger(DefaultNetworkElementManager.class);

	@ManagedProperty(value="#{networkElementDAO}")
	private NetworkElementDAO networkElementDAO;
	
	public void setNetworkElementDAO(NetworkElementDAO networkElementDAO)
	{
		this.networkElementDAO = networkElementDAO;
	}

	@Override
	public NetworkElement getNetworkElement(Long id)
	{
		if(id == null)
		{
			log.debug("Empty argument - return null");
			return null;
		}
		
		return networkElementDAO.getNetworkElement(id);
	}

	@Override
	public Collection<NetworkElement> getNetworkElements(Long... ids)
	{
		if(ArrayUtils.isEmpty(ids))
		{
			log.debug("Empty argument - return null");
			return null;
		}
		
		return networkElementDAO.getNetworkElements(ids);
	}

	@Override
	public void saveNetworkElement(NetworkElement n)
	{
		networkElementDAO.saveNetworkElement(n);
	}

	@Override
	public void saveNetworkElements(NetworkElement... n)
	{
		networkElementDAO.saveNetworkElements(n);
	}

	@Override
	public void deleteNetworkElements(NetworkElement... ne)
	{
		networkElementDAO.deleteNetworkElements(ne);
	}

	@Override
	public int getNumOfTickets(Long id)
	{
		return networkElementDAO.getNumOfTickets(id);
	}

	@Override
	public Collection<NetworkElement> getAllNetworkElements()
	{
		return networkElementDAO.getAllNetworkElements();
	}

}
