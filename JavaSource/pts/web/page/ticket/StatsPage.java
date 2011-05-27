package pts.web.page.ticket;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.util.CollectionUtils;

import pts.controller.network.NetworkElementManager;
import pts.controller.ticket.ProblemManager;
import pts.controller.ticket.TicketManager;
import pts.core.util.BeanFinder;
import pts.model.network.NetworkElement;

@ManagedBean(name="statsPage")
@RequestScoped
public class StatsPage
{
	public static String BEAN_NAME = "statsPage";
	private TicketManager ticketManager;
	private ProblemManager problemManager;
	private NetworkElementManager networkElementManager;
	private Collection<Pair<String, Integer>> netElemToNum;
	
	public StatsPage()
	{
		ticketManager = BeanFinder.findBean(TicketManager.BEAN_NAME, TicketManager.class);
		problemManager = BeanFinder.findBean(ProblemManager.BEAN_NAME, ProblemManager.class);
		networkElementManager = BeanFinder.findBean(NetworkElementManager.BEAN_NAME, NetworkElementManager.class);
	}
	
	public int getNumberOfTickets()
	{
		return ticketManager.getTickets().size();
	}
	
	public int getNumberOfProblems()
	{
		//TODO implement
		return 1;
	}
	
	public Collection<Pair<String, Integer>> getNetElemToNum()
	{
		if(netElemToNum == null)
		{
			netElemToNum = new ArrayList<Pair<String, Integer>>();
			Collection<NetworkElement> neList = networkElementManager.getAllNetworkElements();
			if(!CollectionUtils.isEmpty(neList))
			{
				for(NetworkElement ne : neList)
				{
					netElemToNum.add(new Pair<String, Integer>(
							ne.getName(), 
							networkElementManager.getNumOfTickets(ne.getId())));
				}
			}
		}
		
		return netElemToNum;
	}

	public void setNetElemToNum(Collection<Pair<String, Integer>> netElemToNum)
	{
		this.netElemToNum = netElemToNum;
	}

}
