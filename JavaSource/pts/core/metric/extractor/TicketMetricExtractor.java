package pts.core.metric.extractor;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import pts.controller.network.PropertyHistoryManager;
import pts.core.metric.snmp.SnmpMetric;
import pts.core.metric.snmp.SnmpMetricFactory;
import pts.core.util.BeanFinder;
import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;
import pts.model.ticket.Problem;
import pts.model.ticket.Ticket;

@Deprecated
public class TicketMetricExtractor implements MetricExtractor<Ticket, SnmpMetric>
{
	private static Logger log = Logger.getLogger(TicketMetricExtractor.class);
	private static TicketMetricExtractor INSTANCE = new TicketMetricExtractor();
	private PropertyHistoryManager phManager;
	
	public static TicketMetricExtractor getInstance()
	{
		return INSTANCE;
	}
	
	private TicketMetricExtractor()
	{
		phManager = BeanFinder.findBean("propertyHistoryManager", PropertyHistoryManager.class);
	}
	
	/**
	 * Calculates Ticket Metric.
	 */
	@Override
	public SnmpMetric extract(Ticket ticket)
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
		
		if(metric != null)
		{
			metric = SnmpMetricFactory.createSnmpMetric(calculationResult);
		}
		
		log.debug("Resulting metric " + metric);
		return metric;
	}

	private boolean shouldIncludePropertyValue(Date ticketDate, Date propValueDate)
	{
		return true;
	}
}
