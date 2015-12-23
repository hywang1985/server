package app.ws.clinicos.resources;

import app.ws.clinicos.dao.AppointmentDAO;
import app.ws.clinicos.model.Appointment;
import app.ws.clinicos.model.User;
import app.ws.clinicos.utils.Constants;
import app.ws.clinicos.utils.CustomException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author KIRANN
 */
@Path("/appointments")
public class AppointmentResource extends CommonResource {
    private final AppointmentDAO service = new AppointmentDAO();
    
    @GET
    @Path("{" + Constants.ID_PARAM + "}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAppointmentById(@PathParam(Constants.ID_PARAM) int id) {
        Response.ResponseBuilder builder = null;
        Appointment a;
        try {
            a = service.getAppointmentById(id);
//            populateGetUserLinks(user);
            builder = Response.ok(a);
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
    public Response getAppointments(@QueryParam("aptDate") String aptDate) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        Date date = null;
        if (aptDate == null) {
            date = new Date();
        } else {
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            try {
                date = df.parse(aptDate);
            } catch (ParseException ex) {
                date = new Date();
            }
        }
        List<Appointment> appointments;
        try {
            appointments = service.getAppointments(date);
//            populateUserListLinks(users);
            GenericEntity<List<Appointment>> entity = new GenericEntity<List<Appointment>>(appointments) {
            };
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
    public Response createAppointment(Appointment appointment) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        Appointment u;
        try {
            u = service.createAppointment(appointment);
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
    public Response updateAppointment(@PathParam(Constants.ID_PARAM) int id, Appointment appointment) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        Appointment a;
        try {
            a = service.updateAppointment(appointment);
            builder = Response.ok(a);
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
            service.deleteAppointment(id);
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
}
