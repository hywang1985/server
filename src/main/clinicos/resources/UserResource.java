package app.ws.clinicos.resources;

import app.ws.clinicos.dao.UserDAO;
import app.ws.clinicos.model.User;
import app.ws.clinicos.security.AuthorizationFilter;
import app.ws.clinicos.utils.Constants;
import app.ws.clinicos.utils.CustomException;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Arjun Golabhanvi
 */
@Path(Constants.USERS_PATH)
public class UserResource extends CommonResource {

    private final UserDAO service = new UserDAO();

    @GET
    @Path("{" + Constants.ID_PARAM + "}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserByID(@PathParam(Constants.ID_PARAM) int id) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        User user;
        try {
            user = service.findUserByID(id);
            populateGetUserLinks(user);
            builder = Response.ok(user);
//            if (filter.getNewSessionID() != null) {
//                builder.header(Constants.JSESSIONID, filter.getNewSessionID());
//            } else {
//                builder.header(Constants.JSESSIONID, sessionId);
//            }
            return builder.build();
        } catch (CustomException ex) {
            return handleException(ex, Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUserList() {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        List<User> users;
        try {
            users = service.getUsers();
            populateUserListLinks(users);
            GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
            builder = Response.ok(entity);
//            if (filter.getNewSessionID() != null) {
//                builder.header(Constants.JSESSIONID, filter.getNewSessionID());
//            } else {
//                builder.header(Constants.JSESSIONID, sessionId);
//            }
            return builder.build();
        } catch (CustomException ex) {
            return handleException(ex, Response.Status.NOT_FOUND);
        }
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createUser(User user) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        User u;
        try {
            u = service.createUser(user);
            user.setPassword(null);
            builder = Response.ok(u);
//        setPrivateCacheHeaders(builder, null, Constants.PATIENT_EXPIRE);
//            if (filter.getNewSessionID() != null) {
//                builder.header(Constants.JSESSIONID, filter.getNewSessionID());
//            } else {
//                builder.header(Constants.JSESSIONID, sessionId);
//            }
            return builder.build();
        } catch (CustomException ex) {
            return handleException(ex, Response.Status.BAD_REQUEST);
        }
    }
    
    @PUT
    @Path("{" + Constants.ID_PARAM + "}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateUser(@PathParam(Constants.ID_PARAM) int id, User user) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        User u;
        try {
            u = service.updateUser(user);
            user.setPassword(null);
            builder = Response.ok(u);
//            if (filter.getNewSessionID() != null) {
//                builder.header(Constants.JSESSIONID, filter.getNewSessionID());
//            } else {
//                builder.header(Constants.JSESSIONID, sessionId);
//            }
            return builder.build();
        } catch (CustomException ex) {
            return handleException(ex, Response.Status.BAD_REQUEST);
        }
    }
    
    @DELETE
    @Path("{" + Constants.ID_PARAM + "}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteUser(@PathParam(Constants.ID_PARAM) int id) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;        
        try {
            service.removeUser(id);
            builder = Response.status(Response.Status.NO_CONTENT);
//            if (filter.getNewSessionID() != null) {
//                builder.header(Constants.JSESSIONID, filter.getNewSessionID());
//            } else {
//                builder.header(Constants.JSESSIONID, sessionId);
//            }
            return builder.build();
        } catch (CustomException ex) {
            return handleException(ex, Response.Status.BAD_REQUEST);
        }
    }
    
    private void populateGetUserLinks(User user) {
        // self
        URI selfUri = createUri(getBaseUri(), Constants.USERS_PATH + "/" + user.getId(), null);
        addLink(user, selfUri.toString(), Constants.LINK_REL_SELF, null);
        //update
        URI editUri = createUri(getBaseUri(), Constants.USERS_PATH + "/" + user.getId(), null);
        addLink(user, editUri.toString(), Constants.LINK_REL_EDIT, null);
        //delete        
        URI deleteUri = createUri(getBaseUri(), Constants.USERS_PATH + "/" + user.getId(), null);
        addLink(user, deleteUri.toString(), Constants.LINK_REL_DELETE, null);
    }
    
    private void populateUserListLinks(List<User> users) {
        for(User u : users){
            u.setPassword(null);
            populateGetUserLinks(u);
        }            
    }
}
