package pts.core.sql;

import java.math.BigInteger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;

import pts.core.util.BeanFinder;

/**
 * Postgresql sequence implementation of IdGenerator.
 * 
 */
@ManagedBean(name = "IdGenerator")
@ApplicationScoped
public class PgSqlIdGenerator implements IdGenerator
{
	private static Logger log = Logger.getLogger(PgSqlIdGenerator.class);
	
	@ManagedProperty(value = "#{sessionFactory}")
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;

	public static final String BEAN_NAME = "IdGenerator";

	public static PgSqlIdGenerator getInstance()
	{
		return BeanFinder.findBean(BEAN_NAME, PgSqlIdGenerator.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public Long generateID()
	{
		//log.debug("Start generateID");
		//log.debug("sessionFactory: " + sessionFactory);
		if(sessionFactory == null)
		{
			log.debug("sessionFactory is null. Trying to initialize bean.");
			sessionFactory = BeanFinder.findBean("sessionFactory", SessionFactory.class);
		}
		
		BigInteger id = null;
		Session sess = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = sess.beginTransaction();
			
			id = (BigInteger) sess.createSQLQuery("select get_id()").uniqueResult();
			
			tx.commit();
		} 
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new RuntimeException("Exception when generating ID", e);
		} 
		finally
		{
			sess.close();
		}
		log.debug("generateID: " + id);
		return Long.valueOf(id.toString());
	}
}
