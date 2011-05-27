package pts.core.metric.gather;

import java.util.Map;

import org.apache.log4j.Logger;

import pts.controller.network.PropertyHistoryManager;
import pts.controller.ticket.ActionManager;
import pts.controller.ticket.ProblemManager;
import pts.core.util.BeanFinder;
import pts.model.network.NetworkElement;
import pts.model.network.properties.DatedPropertyValue;
import pts.model.network.properties.PropertyDefinition;
import pts.model.ticket.Action;

/**
 * Gather metrics each time when action is added or deleted.
 *
 */
public aspect ActionUpdateAspect
{
	private static Logger log = Logger.getLogger(ActionUpdateAspect.class);
	
	pointcut updateActionOperation():
		execution( public * ProblemManager.addAction(..) );
	
	pointcut updateActionOperationWithArgs(Long problemID, Action... a):
		updateActionOperation() && args(problemID, a);
	
	pointcut deleteActionOperation():
		execution( public * ActionManager.deleteAction(..) );
	
	pointcut deleteActionOperationWithArgs(Long[] actionIDs):
		deleteActionOperation() && args(actionIDs);
	
	after(Long problemID, Action[] a):
		updateActionOperationWithArgs(problemID, a)
		{
			gatherMetricsForProblem(problemID);
		}
	
	after(Long[] actionIDs):
		deleteActionOperationWithArgs(actionIDs)
		{
			gatherMetricsForAction(actionIDs);
		}
	
	private void gatherMetricsForAction(Long[] actionIDs)
	{
		if(actionIDs != null)
		{
			ActionManager am = BeanFinder.findBean("actionManager", ActionManager.class);
			for (int i = 0; i < actionIDs.length; i++)
			{
				gatherMetricsForProblem(am.getProblemID(actionIDs[i]));
			}
		}
		
	}

	private void gatherMetricsForProblem(Long problemID)
	{
		log.debug("Gather metrics for problem " + problemID + " START");
		
		ProblemManager pm = BeanFinder.findBean("problemManager", ProblemManager.class);
		PropertyHistoryManager phm = BeanFinder.findBean("propertyHistoryManager", PropertyHistoryManager.class);
		
		MetricGatherer<NetworkElement, DatedPropertyValue> metricGatherer = MetricGathererFactory.createNetworkMetricGatherer();
		for(NetworkElement ne : pm.getAffectedNetworkElements(problemID))
		{
			Map<PropertyDefinition, DatedPropertyValue> map = metricGatherer.gather(ne);
			if(!map.isEmpty())
			{
				phm.addPropertyValueToHistory(
						ne, 
						map.values().toArray(new DatedPropertyValue[map.values().size()]));
			}
		}
		
		log.debug("Gather metrics for problem END");
	}
}
