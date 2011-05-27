/**
 * Package contains network model. Model is represented by java beans.
 * The model entities support Hibernate annotations as well as Jenabean annotations.
 * That makes the model easy to maintain. Changes in RDF model automatically are 
 * transformed in changes to Hibernate model. I.e. changing the field names, adding or 
 * removing fields from java beans with result in automatic reconfiguration on program startup.
 * In hibernate this reconfiguration policy is governed by the property applicationContext.xml#hibernate.hbm2ddl.auto(which is
 * by default set to update).
 */
package pts.model.network;