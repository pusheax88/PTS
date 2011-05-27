package pts.controller.network;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;

import pts.dao.network.NetworkDAO;
import pts.model.network.Network;

@ManagedBean(name="networkManager")
@ApplicationScoped
public class DefaultNetworkManager implements NetworkManager
{
	private static Logger log = Logger.getLogger(DefaultNetworkManager.class);

	@ManagedProperty(value="#{networkDAO}")
	private NetworkDAO networkDAO;
	
	public void setNetworkDAO(NetworkDAO networkDAO)
	{
		this.networkDAO = networkDAO;
	}

	@Override
	public Network getNetwork(Long id)
	{
		if(id == null)
		{
			log.debug("Empty argument - returning null");
			return null;
		}
		return networkDAO.getNetwork(id);
	}

	@Override
	public Collection<Network> getNetworks()
	{
		return networkDAO.getNetworks();
	}

	@Override
	public void saveNetwork(Network network)
	{
		networkDAO.saveNetwork(network);
	}

	@Override
	public void saveNetworks(Network... n)
	{
		networkDAO.saveNetworks(n);
	}

	@Override
	public void deleteNetwork(Network network)
	{
		networkDAO.deleteNetwork(network);
	}

	@Override
	public Network getNetworkForNetworkElement(Long networkElementID)
	{
		return networkDAO.getNetworkForNetworkElement(networkElementID);
	}

}
