package pts.core.rdf;

import pts.model.network.Network;

public interface RdfParser
{
	/**
	 * Parses String in "RDF/XML"(that is full format) format to Network.
	 * @see Network
	 */
	public Network parseToNetwork(String rdfContent);
}
