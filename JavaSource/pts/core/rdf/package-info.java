/**
 * Resource Description Framework(RDF format) related code. Contains rdf parser to make model units from RDF/XML content.
 * The goal is to make transparent transition between RDF and Java beans so that resulting RDF conforms NDL schema.
 * Currently Jenabean produces code which does not fully conform to NDL. Yet the xml format is clear enough to write 
 * the sample networks and can be extended for the needs of given project.
 * 
 * @see http://ru.wikipedia.org/wiki/Resource_Description_Framework
 * @see http://www.science.uva.nl/research/sne/ndl
 * @see http://code.google.com/p/jenabean/
 */
package pts.core.rdf;