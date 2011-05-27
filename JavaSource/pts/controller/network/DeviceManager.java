package pts.controller.network;

import java.util.Collection;

import pts.model.network.Device;
import pts.model.network.NetworkElement;

public interface DeviceManager
{
	public static final String BEAN_NAME = "deviceManager";
	public Collection<? extends  NetworkElement> getConnectedToNetworkElements(Device device);
}
