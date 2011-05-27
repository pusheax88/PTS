package pts.core.dia.transform;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import pts.controller.network.DeviceManager;
import pts.core.dia.DiaDiagram;
import pts.core.dia.DiaLayer;
import pts.core.dia.DiaLine;
import pts.core.dia.DiaObject;
import pts.core.dia.DiaWriter;
import pts.core.dia.network.Host;
import pts.core.dia.network.Router;
import pts.core.util.BeanFinder;
import pts.model.network.Device;
import pts.model.network.Network;
import pts.model.network.NetworkElement;

public class Network2Dia
{
	private static Logger log = Logger.getLogger(Network2Dia.class);
	
	public static void networkToDia(Network network, String fileName)
	{
		log.debug("Write network to DIA format START");
		if(CollectionUtils.isEmpty(network.getNetworkElements()))
		{
			log.debug("No network elements - END");
			return;
		}
		
		Map<DiaObject, Collection<DiaObject>> connMap = createConnectionMap(network);
		DiaDiagram diagram = createDiagram(connMap);
		if(!connMap.isEmpty() && diagram != null)
		{
			DiaWriter.write(diagram, fileName);
		}
		
		log.debug("Write network to DIA format END");
	}
	
	public static InputStream networkToDia(Network network)
	{
		try
		{
			log.debug("Write network to DIA format START");
			if(CollectionUtils.isEmpty(network.getNetworkElements()))
			{
				log.debug("No network elements - END");
				return null;
			}
			
			Map<DiaObject, Collection<DiaObject>> connMap = createConnectionMap(network);
			DiaDiagram diagram = createDiagram(connMap);
			if(!connMap.isEmpty() && diagram != null)
			{
				return DiaWriter.getAsInputStream(diagram);
			}
		}
		finally
		{
			log.debug("Write network to DIA format END");
		}
		return null;
	}
	
	private static DiaDiagram createDiagram(Map<DiaObject, Collection<DiaObject>> connMap)
	{
		if(connMap == null || connMap.isEmpty())
		{
			log.debug("Empty connMap");
			return null;
		}
		
		log.debug("Create DiaDiagram START");
		DiaLayer layer = new DiaLayer();
		for(DiaObject obj : connMap.keySet())
		{
			layer.addObjects(obj);
			log.debug("Added new object to layer " + obj);
			Collection<DiaObject> connectedToObjects = connMap.get(obj);
			if(!CollectionUtils.isEmpty(connectedToObjects))
			{
				for(DiaObject toObj : connectedToObjects)
				{
					log.debug("DiaObject to DiaObject connection: " + obj + " to " + toObj);
					DiaLine line = new DiaLine(obj, toObj);
					layer.addObjects(line);
				}
			}
		}
		
		DiaDiagram diag = new DiaDiagram(layer);
		log.debug("Resulting diagram: " + diag);
		log.debug("Create DiaDiagram END");
		return diag;
	}
	
	private static Map<DiaObject, Collection<DiaObject>> createConnectionMap(Network network)
	{
		DeviceManager devMgr = BeanFinder.findBean(DeviceManager.BEAN_NAME, DeviceManager.class);
		Map<DiaObject, Collection<DiaObject>> connectionMap = new LinkedHashMap<DiaObject, Collection<DiaObject>>();
		Map<NetworkElement, DiaObject> netElemToDiaObjectMap = new HashMap<NetworkElement, DiaObject>();
		
		String corner = null;
		for(NetworkElement ne : network.getNetworkElements())
		{
			if(ne instanceof Device)
			{
				Device dev = (Device)ne;
				corner = positionCorner(corner);
				DiaObject diaObject = createOrGetDiaObject(netElemToDiaObjectMap, ne, corner);
				connectionMap.put(diaObject, new HashSet<DiaObject>());
				log.debug("Found device: " + dev);
				Collection<? extends NetworkElement> connTo = devMgr.getConnectedToNetworkElements(dev);
				if(CollectionUtils.isEmpty(connTo))
				{
					log.debug("No connectedTo elements for device " + dev);
					continue;
				}
				
				for(Object connectedToElement : connTo)
				{
					if(connectedToElement instanceof Device)
					{
						Device toDev = (Device)connectedToElement;
						corner = positionCorner(corner);
						DiaObject toDiaObject = createOrGetDiaObject(netElemToDiaObjectMap, toDev, corner);
						connectionMap.get(diaObject).add(
								toDiaObject);
						log.debug("Connected to device: " + toDev);
					}
				}
			}
		}
		return connectionMap;
	}
	
	private static DiaObject createOrGetDiaObject(
			Map<NetworkElement, DiaObject> netElemToDiaObjectMap, 
			NetworkElement nelem,
			String corner)
	{
		DiaObject diaObject = null;
		Device dev = (Device)nelem;
		if(mapContainsNetElem(netElemToDiaObjectMap, nelem))
		{
			log.debug("Found network element in netElemToDiaObjectMap: " + nelem);
			diaObject = getDiaObjectForNetElem(netElemToDiaObjectMap, nelem);
		}
		else
		{
			log.debug("Network element not found in netElemToDiaObjectMap: " + nelem);
			diaObject = dev.getHost() ? new Host(corner) : new Router(corner);
			log.debug("Network element put in netElemToDiaObjectMap.");
			netElemToDiaObjectMap.put(nelem, diaObject);
		}
		
		return diaObject;
	}
	
	private static DiaObject getDiaObjectForNetElem(Map<NetworkElement, DiaObject> map, NetworkElement cmpNe)
	{
		for (NetworkElement ne : map.keySet())
		{
			if(ne.getId().equals(cmpNe.getId()))
			{
				return map.get(ne);
			}
		}
		return null;
	}
	
	private static boolean mapContainsNetElem(Map<NetworkElement, DiaObject> map, NetworkElement cmpNe)
	{
		for (NetworkElement ne : map.keySet())
		{
			if(ne.getId().equals(cmpNe.getId()))
			{
				return true;
			}
		}
		return false;
	}
	
	private static String positionCorner(String corner)
	{
		if(corner == null)
		{
			return "0,0";
		}
		
		String[] coords = corner.split(",");
		int x = Integer.valueOf(coords[0]);
		int y = Integer.valueOf(coords[1]);
		
		if(x >= 40)
		{
			x = 0;
			y += 10;
		}
		else
		{
			x += 5;
		}
		
		return x + "," + y;
	}
}
