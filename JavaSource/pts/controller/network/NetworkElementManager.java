package pts.controller.network;

import java.util.Collection;

import pts.model.network.NetworkElement;

public interface NetworkElementManager
{
	public static final String BEAN_NAME = "networkElementManager";
	public NetworkElement getNetworkElement(Long id);
	public Collection<NetworkElement> getNetworkElements(Long... ids);
	public Collection<NetworkElement> getAllNetworkElements();
	public void saveNetworkElement(NetworkElement n);
	public void saveNetworkElements(NetworkElement... n);
	public void deleteNetworkElements(NetworkElement... ne);
	
	//stats
	public int getNumOfTickets(Long id);
}
