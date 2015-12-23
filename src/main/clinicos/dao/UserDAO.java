package app.ws.clinicos.dao;

import app.ws.clinicos.model.User;
import app.ws.clinicos.utils.CustomException;
import app.ws.clinicos.utils.ErrorMsgs;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Arjun Golabhanvi
 */
public class UserDAO extends BaseDAO {

    public User findUser(String login) {
        Session session = null;
        User user = null;
        try {
            session = beginTransaction();
            user = (User) session.createQuery("select u from User u "
                    + "join u.roles r WHERE u.login = :LOGIN")
                    .setParameter("LOGIN", login).uniqueResult();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBack(session);
        } finally {
            closeSession(session);
        }
        return user;
    }

    public User findUserByID(int id) throws CustomException {
        Session session = null;
        User user = null;
        try {
            session = beginTransaction();
            user = (User) session.createQuery("SELECT u FROM User u "
                    + "JOIN u.roles r WHERE u.id = :ID")
                    .setParameter("ID", id).uniqueResult();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        if (user == null) {
            throw new CustomException(new Exception(ErrorMsgs.USER_NOT_FOUND), ErrorMsgs.INPUT_ERROR);
        }
        user.setPassword(null);
        return user;
    }

    public List<User> getUsers() throws CustomException {
        Session session = null;
        List<User> users = null;
        try {
            session = beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        if (users == null || users.isEmpty()) {
            throw new CustomException(new Exception(ErrorMsgs.USER_NOT_FOUND), ErrorMsgs.INPUT_ERROR);
        }
        return users;
    }

    public User createUser(User u) throws CustomException {
        Session session = null;
        try {
            session = beginTransaction();
            u.setCreatedDate(new Date());
            u.setModifiedDate(new Date());
            int id = (Integer) session.save(u);
            u.setId(id);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        return u;
    }

    public User updateUser(User u) throws CustomException {
        Session session = null;
        try {
            u.setPassword(getUserPassword(u.getLogin()));
            u.setModifiedDate(new Date());
            session = beginTransaction();
            session.update(u);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
        return u;
    }

    public void removeUser(int id) throws CustomException {
        Session session = null;
        try {
            session = beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBackAndThrow(ex, session);
        } finally {
            closeSession(session);
        }
    }

    public String getUserPassword(String login) {
        Session session = null;
        String password = null;
        try {
            session = beginTransaction();
            password = (String) session.createQuery("select u.password from User u where u.login = :LOGIN")
                    .setParameter("LOGIN", login).uniqueResult();
            session.getTransaction().commit();
        } catch (Throwable ex) {
            rollBack(session);
        } finally {
            closeSession(session);
        }
        return password;
    }
}
