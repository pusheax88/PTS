package pts.core.sql;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;

import pts.core.util.BeanFinder;

@ManagedBean(name = "IdGenerator")
@ApplicationScoped
public class PgSqlIdGenerator implements IdGenerator
{
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
		Long id = null;
		Session sess = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = sess.beginTransaction();
			
			id = (Long) sess.createSQLQuery("select get_id()").uniqueResult();
			
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
		return id;
	}
}
