package pts.dao.network;

import java.util.Collection;

import pts.model.network.Network;

public interface NetworkDAO
{
	public Network getNetwork(Long id);
	public Collection<Network> getNetworks();
	public void saveNetwork(Network network);
	public void saveNetworks(Network... n);
	public void deleteNetwork(Network network);
	public Network getNetworkForNetworkElement(Long networkElementID);
}
