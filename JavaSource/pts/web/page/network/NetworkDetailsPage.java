package pts.web.page.network;

import java.io.*;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import pts.controller.network.NetworkManager;
import pts.core.dia.transform.Network2Dia;
import pts.model.network.Network;
import pts.model.network.NetworkElement;

@ManagedBean(name="networkDetailsPage")
@RequestScoped
public class NetworkDetailsPage
{
	private static Logger log = Logger.getLogger(NetworkDetailsPage.class);
	private String networkID;
	private Network network;
	
	@ManagedProperty(value="#{networkManager}")
	private NetworkManager networkManager;
	private Collection<NetworkElement> networkElements;
	
	public NetworkDetailsPage()
	{
		networkID = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("networkID");
	}

	@PostConstruct
	private void init()
	{
		if(!StringUtils.isEmpty(networkID))
		{
			log.debug("Passed networkID = " + networkID);
			network = networkManager.getNetwork(Long.valueOf(networkID));
			log.debug("Network resolved " + network);
		}
		else
		{
			log.debug("Passed networkID is empty!");
		}
	}
	
	public Collection<NetworkElement> getNetworkElements()
	{
		if(networkElements == null && network != null)
		{
			networkElements = network.getNetworkElements();
		}
		return networkElements;
	}

	public void setNetworkElements(Collection<NetworkElement> networkElements)
	{
		this.networkElements = networkElements;
	}

	public Network getNetwork()
	{
		return network;
	}

	public void setNetwork(Network network)
	{
		this.network = network;
	}

	public String getNetworkID()
	{
		return networkID;
	}

	public void setNetworkID(String networkID)
	{
		this.networkID = networkID;
	}

	public void setNetworkManager(NetworkManager networkManager)
	{
		this.networkManager = networkManager;
	}
	
	public void saveAsDiagramAction()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

	    response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
	    response.setContentType("application/xml"); // Check http://www.w3schools.com/media/media_mimeref.asp for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
	    response.setHeader("Content-disposition", "attachment; filename=\"network.dia\""); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.

	    BufferedInputStream input = null;
	    BufferedOutputStream output = null;

	    try 
	    {
	        input = new BufferedInputStream(Network2Dia.networkToDia(network));
	        output = new BufferedOutputStream(response.getOutputStream());

	        byte[] buffer = new byte[10240];
	        for (int length; (length = input.read(buffer)) > 0;) 
	        {
	            output.write(buffer, 0, length);
	        }
	    } 
	    catch(Exception e)
	    {
	    	log.debug(e.getMessage(), e);
	    }
	    finally 
	    {
	        close(output);
	        close(input);
	    }

	    facesContext.responseComplete(); // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}
	
	private void close(InputStream is)
	{
		if(is != null)
		{
			try
			{
				is.close();
			} 
			catch (IOException e)
			{
				log.debug(e.getMessage(), e);
			}
		}
	}
	
	private void close(OutputStream os)
	{
		if(os != null)
		{
			try
			{
				os.close();
			} 
			catch (IOException e)
			{
				log.debug(e.getMessage(), e);
			}
		}
	}

}
