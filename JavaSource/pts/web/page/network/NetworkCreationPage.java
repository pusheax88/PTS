package pts.web.page.network;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import pts.controller.network.NetworkManager;
import pts.core.rdf.RdfParser;
import pts.core.util.BeanFinder;
import pts.core.util.JsfTool;
import pts.model.network.Network;

@ManagedBean(name="networkCreationPage")
@RequestScoped
public class NetworkCreationPage
{
	private static Logger log = Logger.getLogger(NetworkCreationPage.class);
	public static String BEAN_NAME = "networkCreationPage";

	public static NetworkCreationPage getInstance()
	{
		return (NetworkCreationPage)FacesContext.getCurrentInstance().
			getExternalContext().getRequestMap().get(BEAN_NAME);
	}

	private String networkRdfDescription;

	public String getNetworkRdfDescription()
	{
		return networkRdfDescription;
	}

	public void setNetworkRdfDescription(String networkRdfDescription)
	{
		this.networkRdfDescription = networkRdfDescription;
	}

	//Navigation
	public void submitNetworkAction()
	{
		NetworkManager networkManager = BeanFinder.findBean("networkManager", NetworkManager.class);
		RdfParser rdfParser = BeanFinder.findBean("rdfParser", RdfParser.class);
		
		//log.trace("Creating network from rdf description: \n" + networkRdfDescription);
		Network network = rdfParser.parseToNetwork(networkRdfDescription);
		
		log.debug("Parsed Network from rdf: " + network);
		networkManager.saveNetwork(network);
		
		JsfTool.redirect("/pages/networks.jsf");
	}
}
