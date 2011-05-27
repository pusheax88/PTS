package pts.web.page.network;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;

import pts.controller.network.NetworkElementManager;
import pts.controller.network.NetworkManager;
import pts.model.network.NetworkElement;

@ManagedBean(name="networkElemDetailsPage")
@RequestScoped
public class NetworkElemDetailsPage
{
	private static Logger log = Logger.getLogger(NetworkElemDetailsPage.class);
	private String networkID;
	private String networkElementID;
	private NetworkElement networkElem;
	private String neType;
	
	@ManagedProperty(value="#{networkElementManager}")
	private NetworkElementManager networkElementManager;
	
	@ManagedProperty(value="#{networkManager}")
	private NetworkManager networkManager;
	
	public NetworkElemDetailsPage()
	{
		networkElementID = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("networkElementID");
	}

	@PostConstruct
	private void init()
	{
		if(!StringUtils.isEmpty(networkElementID))
		{
			log.debug("Initializing for networkElementID = " + networkElementID);
			networkElem = networkElementManager.getNetworkElement(Long.valueOf(networkElementID));
			log.debug("Resolved NetworkElement " + networkElem);
			networkID = networkManager.getNetworkForNetworkElement(Long.valueOf(networkElementID)).getId().toString();
			log.debug("Resolved networkID = " + networkID);
			
			//TODO create method to get type of NetworkElement AKA discriminator value
			neType = "NetworkElement";
		}
		else
		{
			log.debug("Passed networkElementID is empty!");
		}
	}
	
	public void setNetworkElementManager(NetworkElementManager networkElementManager)
	{
		this.networkElementManager = networkElementManager;
	}

	public void setNetworkManager(NetworkManager networkManager)
	{
		this.networkManager = networkManager;
	}

	public String getNetworkID()
	{
		return networkID;
	}

	public void setNetworkID(String networkID)
	{
		this.networkID = networkID;
	}

	public String getNetworkElementID()
	{
		return networkElementID;
	}

	public void setNetworkElementID(String networkElementID)
	{
		this.networkElementID = networkElementID;
	}

	public NetworkElement getNetworkElem()
	{
		return networkElem;
	}

	public void setNetworkElem(NetworkElement ne)
	{
		this.networkElem = ne;
	}

	public String getNeType()
	{
		return neType;
	}

	public void setNeType(String neType)
	{
		this.neType = neType;
	}
}
