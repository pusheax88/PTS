/**
 * Package contains rdf helper beans.
 */
package pts.core.rdf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;

import pts.core.util.BeanFinder;
import pts.model.network.Network;
import thewebsemantic.RDF2Bean;
import thewebsemantic.binding.Jenabean;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFReader;

/**
 * Default implementation of RdfParser.
 */
@ManagedBean(name="rdfParser")
@ApplicationScoped
public class DefaultRdfParser implements RdfParser
{
	private static Logger log = Logger.getLogger(DefaultRdfParser.class);
	public static final String BEAN_NAME = "rdfParser";
	
	public static RdfParser getInstance()
	{
		return BeanFinder.findBean(BEAN_NAME, DefaultRdfParser.class);
	}

	@Override
	public Network parseToNetwork(String rdfContent)
	{
		try
		{
			log.trace("Trying to parse Network from String: \n" + rdfContent);
			Model m = createModelFromString(rdfContent);
			Jenabean.instance().bind(m);
			
			RDF2Bean reader = new RDF2Bean(m);
	        Collection<Network> networks = reader.load(Network.class);
	        if(!networks.isEmpty())
	        {
	        	return networks.iterator().next();
	        }
		}
		catch(UnsupportedEncodingException e)
		{
			log.error("UnsupportedEncodingException", e);
			throw new RuntimeException("UnsupportedEncodingException", e);
		}
		        
        throw new IllegalArgumentException("Could not parse Network from passed argument!");
	}
	
	private Model createModelFromString(String rdfContent) throws UnsupportedEncodingException
	{
		Model model = ModelFactory.createDefaultModel();
		RDFReader rdfReader = model.getReader();
		
		InputStream is = new ByteArrayInputStream(rdfContent.getBytes("UTF-8"));
		rdfReader.read(model, is, null);
		
		return model;
	}

}
