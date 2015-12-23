package app.ws.clinicos.resources;

import app.ws.clinicos.dao.DoctorDAO;
import app.ws.clinicos.model.Doctor;
import app.ws.clinicos.utils.Constants;
import app.ws.clinicos.utils.CustomException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Arjun Golabhanvi
 */
@Path(Constants.DOCTORS_PATH)
public class DoctorResource extends CommonResource {
    
    private final DoctorDAO service = new DoctorDAO();

    @GET
    @Path("{" + Constants.ID_PARAM + "}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDoctorByID(@PathParam(Constants.ID_PARAM) int id) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        Doctor doctor;
        try {
            doctor = service.findDoctorByID(id);
//            populateGetUserLinks(user);
            builder = Response.ok(doctor);
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
}
