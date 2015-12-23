package app.ws.clinicos.dao;

import app.ws.clinicos.model.IPDDetails;
import app.ws.clinicos.utils.CustomException;
import app.ws.clinicos.model.Patient;
import app.ws.clinicos.model.PatientList;
import app.ws.clinicos.utils.Constants;
import app.ws.clinicos.utils.ErrorMsgs;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author Arjun Golabhanvi
 */
public class PatientDAO extends BaseDAO {

    public Patient getPatientByMRN(long mrn) throws CustomException {
        Patient patient = null;
        Session session = null;
        try {
            session = beginTransaction();
            patient = (Patient) session.createQuery("from Patient p where p.mrn = :MRN")
                    .setParameter("MRN", mrn).uniqueResult();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        if (patient == null) {
            throw new CustomException(ErrorMsgs.MRN_NOT_FOUND, ErrorMsgs.INPUT_ERROR);
        }

        if (patient.getCategory().equals(Patient.Category.IPD)) {
            IPDDetails ipdDetails = null;
            try {
                session = beginTransaction();
                ipdDetails = (IPDDetails) session.createQuery("from IPDDetails ipd where ipd.mrn = :MRN")
                        .setParameter("MRN", patient.getMrn()).uniqueResult();
                session.getTransaction().commit();
            } catch (Throwable ex) {
                rollBackAndThrow(ex, session);
            } finally {
                closeSession(session);
            }
            patient.setIpdDetails(ipdDetails);
        }
        return patient;
    }

    public PatientList getPatientList(Date visitDate, int startIndex, int size) throws CustomException {
        PatientList patients = new PatientList();
        Session session = null;
        String countQuery = "select count(*) from Patient p where p.visitDate = :visitDate";
        try {
            session = beginTransaction();
            int count = ((Long) session.createQuery(countQuery)
                    .setParameter(Constants.VISIT_DATE, visitDate).iterate().next()).intValue();
            closeSession(session);
            session = beginTransaction();
            Query query = session.createQuery("from Patient p where p.visitDate = :visitDate");
            query.setParameter(Constants.VISIT_DATE, visitDate);
            List<Patient> pl = query.setFirstResult(startIndex).setMaxResults(size).list();
            patients.setPatient(pl);
            if (count > (startIndex + size)) {
                patients.setMore(count - (startIndex + size));
            }
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        if (patients.getPatient() == null || patients.getPatient().isEmpty()) {
            throw new CustomException(ErrorMsgs.NO_DATA, ErrorMsgs.DB_ERROR);
        }
        return patients;
    }
    
    public PatientList getIPDPatientList(int startIndex, int size) throws CustomException {
        PatientList patients = new PatientList();
        Session session = null;
        String countQuery = "select count(*) from IPDDetails ipd where ipd.dischargeDate is null";
        try {
            session = beginTransaction();
            int count = ((Long) session.createQuery(countQuery).iterate().next()).intValue();
            closeSession(session);
            session = beginTransaction();
            Query query = session.createSQLQuery("select p.* from patients p, "
                    + "ipd_details ipd where p.mrn = ipd.mrn and ipd.discharge_date is null")
                    .addEntity(Patient.class);
            List<Patient> pl = query.setFirstResult(startIndex).setMaxResults(size).list();
            patients.setPatient(pl);
            if (count > (startIndex + size)) {
                patients.setMore(count - (startIndex + size));
            }
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        if (patients.getPatient() == null || patients.getPatient().isEmpty()) {
            throw new CustomException(ErrorMsgs.NO_DATA, ErrorMsgs.DB_ERROR);
        }
        return patients;
    }

    public PatientList searchPatient(String searchParam) throws CustomException {
        PatientList patients = new PatientList();
        Session session = null;
        FullTextSession fullTextSession = null;
        Transaction tx = null;
        try {
            session = openSession();
            fullTextSession = Search.getFullTextSession(session);
            tx = fullTextSession.beginTransaction();
            QueryBuilder qb = fullTextSession.getSearchFactory()
                    .buildQueryBuilder().forEntity(Patient.class).get();
            org.apache.lucene.search.Query query = qb
                    .keyword()
                    .onFields("mrn", "firstName", "lastName", "mobile")
                    .matching(searchParam)
                    .createQuery();

            org.hibernate.Query hibQuery
                    = fullTextSession.createFullTextQuery(query, Patient.class);

            List<Patient> result = hibQuery.list();
            patients.setPatient(result);
            tx.commit();
        } catch (Throwable ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new CustomException(ex, ErrorMsgs.DB_ERROR);
        } finally {
            if (fullTextSession != null) {
                fullTextSession.close();
            }
        }
        if (patients.getPatient() == null || patients.getPatient().isEmpty()) {
            throw new CustomException(ErrorMsgs.NO_DATA, ErrorMsgs.DB_ERROR);
        }
        return patients;
    }

    public Patient createPatient(Patient p) throws CustomException {
        Session session1 = null;
        Session session2 = null;
        try {
            p.setCreatedDate(new Date());
            p.setModifiedDate(new Date());
            session1 = beginTransaction();
            long mrn = (Long) session1.save(p);
            p.setMrn(mrn);
            if (p.getCategory().equals(Patient.Category.IPD)) {
                IPDDetails ipdDetails = p.getIpdDetails();
                ipdDetails.setMrn(p.getMrn());
                ipdDetails.setCreatedDate(new Date());
                ipdDetails.setModifiedDate(new Date());
                session2 = beginTransaction();
                session2.save(ipdDetails);
                session2.getTransaction().commit();
            }
            session1.getTransaction().commit();
        } catch (Throwable ex) {
            rollBack(session1);
            rollBack(session2);
            throw new CustomException(ex, ErrorMsgs.DB_ERROR);
        } finally {
            closeSession(session1);
            closeSession(session2);
        }
        return p;
    }

    public Patient updatePatient(Long mrn, Patient p) throws CustomException {
        Session session = null;
        try {
            session = beginTransaction();
            p.setModifiedDate(new Date());
            session.update(p);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        return p;
    }

    public IPDDetails updateIpdDetails(IPDDetails ipd) throws CustomException {
        Session session = null;
        try {
            session = beginTransaction();
            session.update(ipd);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        return ipd;
    }

    public void deletePatient(Long mrn) throws CustomException {
        Session session = null;
        try {
            session = beginTransaction();
            Patient patient = (Patient) session.get(Patient.class, mrn);
            session.delete(patient);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
    }
}
