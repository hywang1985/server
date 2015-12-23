package app.ws.clinicos.dao;

import app.ws.clinicos.application.HibernateConfig;
import app.ws.clinicos.utils.CustomException;
import app.ws.clinicos.utils.ErrorMsgs;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 *
 * @author Arjun Golabhanvi
 */
public abstract class BaseDAO {

    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    protected Session beginTransaction() throws HibernateException {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }
    
    protected Session openSession() throws HibernateException {
        return sessionFactory.openSession();
    }
    
    protected void rollBackAndThrow (Throwable ex, Session session) throws CustomException {
        if (session != null) {
            session.getTransaction().rollback();
        }
        throw new CustomException(ex, ErrorMsgs.DB_ERROR);
    }    
    
    protected void rollBack(Session session) {
        if (session != null) {
            session.getTransaction().rollback();
        }
    }
    
    protected void closeSession(Session session){
        if(session != null) {
            session.close();
        }
    }
}
