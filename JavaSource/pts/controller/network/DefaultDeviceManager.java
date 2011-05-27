package pts.controller.network;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;

import pts.dao.network.NetworkElementDAO;
import pts.model.network.Device;
import pts.model.network.NetworkElement;

@ManagedBean(name="deviceManager")
@ApplicationScoped
public class DefaultDeviceManager implements DeviceManager
{
	private static Logger log = Logger.getLogger(DefaultDeviceManager.class);

	@ManagedProperty(value="#{networkElementDAO}")
	private NetworkElementDAO networkElementDAO;
	
	public void setNetworkElementDAO(NetworkElementDAO networkElementDAO)
	{
		this.networkElementDAO = networkElementDAO;
	}

	@Override
	public Collection<? extends NetworkElement> getConnectedToNetworkElements(Device device)
	{
		return networkElementDAO.getConnectedToForNetworkElement(device.getId());
	}

}
