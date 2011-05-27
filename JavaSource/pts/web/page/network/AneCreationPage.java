package pts.web.page.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import pts.controller.network.NetworkElementManager;
import pts.controller.network.NetworkManager;
import pts.controller.ticket.ProblemManager;
import pts.core.util.BeanFinder;
import pts.core.util.JsfTool;
import pts.model.network.Network;
import pts.model.network.NetworkElement;

@ManagedBean(name="aneCreationPage")
@RequestScoped
public class AneCreationPage
{
	private static Logger log = Logger.getLogger(AneCreationPage.class);
	public static String BEAN_NAME = "aneCreationPage";
	
	@ManagedProperty(value="#{networkManager}")
	private NetworkManager networkManager;
	
	private String problemID;
	private String ane;
	private SelectItem[] allNetworkElements;
	
	public AneCreationPage()
	{
		problemID = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("problemID");
	}

	public static AneCreationPage getInstance()
	{
		return (AneCreationPage)FacesContext.getCurrentInstance().
			getExternalContext().getRequestMap().get(BEAN_NAME);
	}

	public void setNetworkManager(NetworkManager networkManager)
	{
		this.networkManager = networkManager;
	}

	public String getProblemID()
	{
		return problemID;
	}

	public void setProblemID(String problemID)
	{
		this.problemID = problemID;
	}

	public String getAne()
	{
		return ane;
	}

	public void setAne(String ane)
	{
		this.ane = ane;
	}

	public SelectItem[] getAllNetworkElements()
	{
		if(allNetworkElements == null)
		{
			Collection<NetworkElement> neCollection = getNetworkElems();
			SelectItem[] res = new SelectItem[neCollection.size() + 1];
			res[0] = new SelectItem("0", "");
			
			int i = 1;
			for (Iterator<NetworkElement> iterator = neCollection.iterator(); iterator.hasNext();)
			{
				NetworkElement ne = iterator.next();
				res[i] = new SelectItem(ne.getId(), ne.getName());
				i++;
			}
			allNetworkElements = res;
		}
		
		return allNetworkElements;
	}
	
	private Collection<NetworkElement> getNetworkElems()
	{
		Collection<NetworkElement> result = new ArrayList<NetworkElement>();
		Collection<Network> networks = networkManager.getNetworks();
		
		if(!CollectionUtils.isEmpty(networks))
		{
			for(Network n : networks)
			{
				result.addAll(n.getNetworkElements());
			}
		}
		
		return result;
	}

	public void setAllNetworkElements(SelectItem[] allNetworkElements)
	{
		this.allNetworkElements = allNetworkElements;
	}
	
	//Navigation
	public void submitAneAction()
	{
		ProblemManager problemManager = BeanFinder.findBean("problemManager", ProblemManager.class);
		NetworkElementManager neManager = BeanFinder.findBean("networkElementManager", NetworkElementManager.class);
		log.debug("Saving ane for problem " + problemID + " with fields\n" + 
				ane);
		
		NetworkElement ne = null;
		if(!"0".equals(ane) && ane != null)
		{
			ne = neManager.getNetworkElement(Long.valueOf(ane));
			problemManager.addAne(Long.valueOf(problemID), ne);
		}
		else
		{
			log.debug("ANE was not saved - ane is null or 0");
		}
		
		JsfTool.redirect("/pages/problem_details.jsf?problemID=" + problemID);
	}

}
