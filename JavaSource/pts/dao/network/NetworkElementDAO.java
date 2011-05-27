package pts.dao.network;

import java.util.Collection;

import pts.model.network.NetworkElement;

public interface NetworkElementDAO
{
	public NetworkElement getNetworkElement(Long id);
	public Collection<NetworkElement> getNetworkElements(Long... ids);
	public Collection<NetworkElement> getAllNetworkElements();
	public void saveNetworkElement(NetworkElement n);
	public void saveNetworkElements(NetworkElement... n);
	public void deleteNetworkElements(NetworkElement... ne);
	
	public Collection<? extends NetworkElement> getConnectedToForNetworkElement(Long id);
	
	//stats
	public int getNumOfTickets(Long id);
}
