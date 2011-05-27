package pts.core.dia;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import pts.core.dia.network.Host;
import pts.core.dia.network.Router;

public class DiaWriter
{
	private static Logger log = Logger.getLogger(DiaWriter.class);
	
	public static void write(DiaDiagram diagram, String fileName)
	{
		try
		{
			log.debug("Saving DIA diagram to file " + fileName);
			JAXBContext context = JAXBContext.newInstance(DiaDiagram.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
			
			marshaller.marshal(diagram, new FileWriter(fileName));
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}
	
	public static InputStream getAsInputStream(DiaDiagram diagram)
	{
		try
		{
			log.debug("Saving DIA diagram to input stream");
			JAXBContext context = JAXBContext.newInstance(
					DiaDiagram.class, DiaLine.class, Host.class, Router.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
			
			StringWriter result = new StringWriter();
			marshaller.marshal(diagram, result);
			
			return new ByteArrayInputStream(result.toString().getBytes("UTF-8"));
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
