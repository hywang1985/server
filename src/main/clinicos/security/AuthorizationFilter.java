package app.ws.clinicos.security;

import app.ws.clinicos.dao.UserDAO;
import app.ws.clinicos.utils.Constants;
import app.ws.clinicos.utils.ErrorMsgs;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.session.ExpiredSessionException;

/**
 *  It's a helper class which assists other resources to check whether provided user has permission to invoke their methods.
 * @author Arjun Golabhanvi
 * @since 16 Jan, 2014
 * @version 1.0
 */
public class AuthorizationFilter {
    
    private String newSessionID = null;
    
    /**
     * It works as below:
     * For all admin operations, active session is mandatory.
     * For non-admin activity, if user is remembered and session expired, fresh login will be done and permission check will be done. 
     * If subject is authentic and permitted to do transaction, true will be returned.
     * Or else exception with status code 401 or 403 will be returned.
     * @param sessionId
     * @param permission
     * @return true/false
     * @throws ForbiddenException
     * @throws NotAuthorizedException
     */
    public boolean isPermitted(String sessionId, String permission) throws ForbiddenException, NotAuthorizedException {
        if (sessionId != null && !sessionId.isEmpty()) {
            Subject subject = new Subject.Builder().sessionId(sessionId).buildSubject();
            
            if (permission.equals(Constants.ADMIN_ACTIVITY) && !subject.isAuthenticated()) {
                throw new NotAuthorizedException(ErrorMsgs.ADMIN_ACTIVITY_ERROR);
            }
            
            if (!subject.isAuthenticated() && !RememberMeService.isRemembered(sessionId)) {
                throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
            }

            try {
                if (subject.isAuthenticated()) {
                    if (!subject.isPermitted(permission)) {
                        throw new ForbiddenException(ErrorMsgs.NOT_ALLOWED);
                    }
                    return true;
                }

                if (RememberMeService.isRemembered(sessionId)) {
                    String login = RememberMeService.getRememberedUser(sessionId);
                    try {
                        String password = (login != null) ? new UserDAO().getUserPassword(login) : null;
                        UsernamePasswordToken token = new UsernamePasswordToken(login, password);
                        subject = SecurityUtils.getSubject();
                        subject.login(token);
                    } catch (Throwable ex) {
                        throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
                    }

                    if (!subject.isAuthenticated()) {
                        throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
                    }

                    RememberMeService.rememberMe(login, (String) subject.getSession().getId());
                    newSessionID = (String) subject.getSession().getId();
                    if (!subject.isPermitted(permission)) {
                        RememberMeService.rememberMe(login, sessionId);
                        throw new ForbiddenException(ErrorMsgs.NOT_ALLOWED);
                    }
                    return true;
                }
            } catch (ExpiredSessionException ex){
                throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
            }
        }
        throw new ForbiddenException(ErrorMsgs.NO_SESSION_ID);
    }
      
    /**
     * Return newSessionID on fresh login.
     * @return newSessionID
     */
    public String getNewSessionID() {
        return newSessionID;
    }    
}
