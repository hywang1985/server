package app.ws.clinicos.dao;

import app.ws.clinicos.model.Appointment;
import app.ws.clinicos.utils.CustomException;
import app.ws.clinicos.utils.ErrorMsgs;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author KIRANN
 */
public class AppointmentDAO extends BaseDAO {
    
    public Appointment getAppointmentById(int id) throws CustomException {
        Session session = null;
        Appointment a = null;
        try {
            session = beginTransaction();
            a = (Appointment) session.createQuery("from Appointment a WHERE a.id = :ID")
                    .setParameter("ID", id).uniqueResult();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        return a;        
    }
    
    public List<Appointment> getAppointments(Date aptDate) throws CustomException {
        Session session = null;
        List<Appointment> appointments = null;
        try {
            session = beginTransaction();
            appointments = session.createQuery("from Appointment a where a.date = :APTDATE")
                    .setParameter("APTDATE", aptDate).list();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        if (appointments == null || appointments.isEmpty()) {
            throw new CustomException(new Exception(ErrorMsgs.USER_NOT_FOUND), ErrorMsgs.INPUT_ERROR);
        }
        return appointments;        
    }
    
    public Appointment createAppointment(Appointment a) throws CustomException {
        Session session = null;
        try {
            session = beginTransaction();
            a.setCreatedDate(new Date());
            a.setModifiedDate(new Date());
            int id = (Integer) session.save(a);
            a.setId(id);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        return a;        
    }
    
    public Appointment updateAppointment(Appointment a) throws CustomException {
        Session session = null;
        try {
            a.setModifiedDate(new Date());
            session = beginTransaction();
            session.update(a);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        return a;
    }

    public void deleteAppointment(int id) throws CustomException {
        Session session = null;
        try {
            session = beginTransaction();
            Appointment a = (Appointment) session.get(Appointment.class, id);
            session.delete(a);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
    }
}
