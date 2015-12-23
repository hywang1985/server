package app.ws.clinicos.dao;

import app.ws.clinicos.model.Doctor;
import app.ws.clinicos.utils.CustomException;
import app.ws.clinicos.utils.ErrorMsgs;
import org.hibernate.Session;

/**
 *
 * @author Arjun Golabhanvi
 */
public class DoctorDAO extends BaseDAO {

    public Doctor findDoctorByID(int id) throws CustomException {
        Doctor doctor = null;
        Session session = null;
        try {
            session = beginTransaction();
            doctor = (Doctor) session.createQuery("from Doctor d where d.id = :ID")
                    .setParameter("ID", id).uniqueResult();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        if (doctor == null) {
            throw new CustomException(ErrorMsgs.DOCTOR_NOT_FOUND, ErrorMsgs.INPUT_ERROR);
        }
        return doctor;
    }
}
