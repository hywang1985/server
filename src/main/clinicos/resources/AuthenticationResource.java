package app.ws.clinicos.resources;

import app.ws.clinicos.dao.UserDAO;
import app.ws.clinicos.model.User;
import app.ws.clinicos.utils.Constants;
import app.ws.clinicos.utils.ErrorMsgs;
import app.ws.clinicos.security.RememberMeService;
import app.ws.clinicos.utils.CustomException;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.jboss.resteasy.util.Base64;

/**
 *  It's a gateway resource which controls user access to application.
 * @author Arjun Golabhanvi
 */
@Path(Constants.AUTH_PATH)
public class AuthenticationResource extends CommonResource {
    
    private final UserDAO service = new UserDAO();

    @GET
    @Path(Constants.AUTH_LOGIN_PATH)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response login() {   
        String enToken = Constants.EMPTY_STRING;        
        Response.ResponseBuilder builder = null;
        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
        if (sessionId != null && !sessionId.isEmpty())
            return login(sessionId);
        String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
        String remHeader = headers.getHeaderString(Constants.REMEMBER_ME);
        boolean rememberMe = remHeader != null ? remHeader.equalsIgnoreCase("true") : false;
        
        if (authHeader == null) {
            throw new NotAuthorizedException(ErrorMsgs.NO_AUTH_DATA);
        }
        try {
            enToken = new String(Base64.decode(authHeader.replaceFirst("Basic ", "")));
        } catch (IOException ex) {
            throw new NotAuthorizedException(ErrorMsgs.INVALID_AUTH_DATA);
        }

        final StringTokenizer tokenizer = new StringTokenizer(enToken, ":");
        final String user = tokenizer.nextToken();
        final String passwd = tokenizer.nextToken();
        UsernamePasswordToken token = new UsernamePasswordToken(user, passwd);
        
        try {
            SecurityUtils.getSubject().login(token);
            if(rememberMe)
                RememberMeService.rememberMe(user, (String)SecurityUtils.getSubject().getSession().getId());
        } catch (UnknownAccountException uae) {
            throw new NotAuthorizedException(ErrorMsgs.UNKNOWN_ACCOUNT);
        } catch (IncorrectCredentialsException ice) {
            throw new NotAuthorizedException(ErrorMsgs.INCORRECT_CREDS);
        } catch (AuthenticationException e) {
            throw new NotAuthorizedException(ErrorMsgs.INVALID_AUTH_DATA);
        }
        User u = service.findUser(user);
        u.setPassword(null);
        builder = Response.ok(u);
        builder.header(Constants.JSESSIONID, SecurityUtils.getSubject().getSession().getId());
        return builder.build();
    }
    
    private Response login(String sessionId) {
        Response.ResponseBuilder builder = null;
        String newSessionID = null;
        Integer userId = null;
        Subject subject = new Subject.Builder().sessionId(sessionId).buildSubject();
        if (!subject.isAuthenticated() && !RememberMeService.isRemembered(sessionId)) {
            throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
        }
        try {
            if (subject.isAuthenticated()) {
                userId = (Integer) subject.getPrincipals().getPrimaryPrincipal();
                builder = Response.ok(service.findUserByID(userId));
                builder.header(Constants.JSESSIONID, SecurityUtils.getSubject().getSession().getId());
                return builder.build();
            } else if (RememberMeService.isRemembered(sessionId)) {
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
                userId = (Integer) subject.getPrincipals().getPrimaryPrincipal();
                newSessionID = (String) subject.getSession().getId();
                RememberMeService.rememberMe(login, newSessionID);
                builder = Response.ok(service.findUserByID(userId));
                builder.header(Constants.JSESSIONID, newSessionID);
                return builder.build();
            } else {
                throw new ExpiredSessionException();
            }
        } catch (ExpiredSessionException ex) {
            throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
        } catch (CustomException ex) {
            throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
        }
    }

    @GET
    @Path(Constants.AUTH_LOGOUT_PATH)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response logout() {
        Response.ResponseBuilder builder = null;
        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
        RememberMeService.forgetMe(sessionId);
        try {
            if (sessionId != null) {
                Subject subject = new Subject.Builder().sessionId(sessionId).buildSubject();
                if (subject.isAuthenticated()) {
                    subject.logout();
                    builder = Response.ok();
                } else {
                    throw new NotAuthorizedException(ErrorMsgs.INVALID_SESSION);
                }
            } else {
                throw new NotAuthorizedException(ErrorMsgs.NO_SESSION_ID);
            }
        } catch (UnknownSessionException ex) {
            throw new NotAuthorizedException(ErrorMsgs.SESSION_EXPIRED);
        } catch (NotAuthorizedException ex){
            throw ex;
        }catch (Exception ex) {
            throw new NotAuthorizedException(ErrorMsgs.UNEXPECTED_ERROR);
        }
        return builder.build();
    }
}
