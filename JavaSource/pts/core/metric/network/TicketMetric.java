package pts.core.metric.network;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import pts.controller.network.PropertyHistoryManager;
import pts.core.metric.Metric;
import pts.core.metric.snmp.SnmpMetric;
import pts.core.util.BeanFinder;
import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;
import pts.model.ticket.Problem;
import pts.model.ticket.Ticket;

public class TicketMetric implements Metric<Integer>
{
	private static Logger log = Logger.getLogger(TicketMetric.class);
	
	private PropertyHistoryManager phManager;
	private Ticket ticket;
	
	public TicketMetric(Ticket ticket)
	{
		this.ticket = ticket;
		phManager = BeanFinder.findBean("propertyHistoryManager", PropertyHistoryManager.class);
	}

	@Override
	public Integer calculate()
	{
		if(CollectionUtils.isEmpty(ticket.getProblems()))
		{
			return null;
		}
		
		SnmpMetric metric = null;
		int calculationResult = 0;
		
		for(Problem p : ticket.getProblems())
		{
			if(CollectionUtils.isEmpty(p.getAffectedNetworkElements()))
			{
				continue;
			}
			for(NetworkElement ne : p.getAffectedNetworkElements())
			{
				if(CollectionUtils.isEmpty(ne.getProperties()))
				{
					continue;
				}
				
				for(PropertyDefinition propDef : ne.getProperties())
				{
					for(DatedPropertyValue propVal : phManager.getPropertyHistory(ne, propDef))
					{
						if(!shouldIncludePropertyValue(ticket.getTicketDate(), propVal.getDate()))
						{
							log.debug("Ignoring property " + propVal);
							continue;
						}
						if(metric == null)
						{
							metric = new SnmpMetric(propDef, propVal);
						}
						else
						{
							metric.setDefinition(propDef);
							metric.setValue(propVal);
						}
						
						calculationResult += metric.calculate();
					}
				}
			}
		}
		
		log.debug("Resulting calculationResult = " + calculationResult);
		return calculationResult;
	}
	
	private boolean shouldIncludePropertyValue(Date ticketDate, Date propValueDate)
	{
		return true;
	}

}
