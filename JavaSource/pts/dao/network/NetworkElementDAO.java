package pts.dao.network;

import pts.model.network.NetworkElement;

public interface NetworkElementDAO
{
	public NetworkElement getNetworkElement(Long id);
	public void saveNetworkElement(NetworkElement n);
	public void deleteNetworkElement(NetworkElement ne);
}
