package app.ws.clinicos.resources;

import app.ws.clinicos.dao.PatientDAO;
import app.ws.clinicos.model.IPDDetails;
import app.ws.clinicos.utils.CustomException;
import app.ws.clinicos.model.Patient;
import app.ws.clinicos.model.PatientList;
import app.ws.clinicos.security.AuthorizationFilter;
import app.ws.clinicos.utils.Constants;
import app.ws.clinicos.utils.ErrorMsgs;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Arjun Golabhanvi
 */
@Path(Constants.PATIENTS_PATH)
public class PatientResource extends CommonResource {

    private final PatientDAO service = new PatientDAO();

    @GET
    @Path("{" + Constants.ID_PARAM + "}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getPatientByMRN(@PathParam(Constants.ID_PARAM) long mrn) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
//        if(headers.getRequestHeader(HttpHeaders.IF_NONE_MATCH) != null){
//            builder = handleConditionalRequest(mrn);
//            if(builder != null)
//               return builder.build();
//        }
        Patient patient;
        try {
            patient = service.getPatientByMRN(mrn);
            populateGetPatientLinks(patient);
            builder = Response.ok(patient);
//        setPrivateCacheHeaders(builder, null, Constants.PATIENT_EXPIRE);
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
    public Response getPatientList(@QueryParam(Constants.VISIT_DATE) String visitDate,
            @QueryParam(Constants.START_INDEX) int start, @QueryParam(Constants.SIZE) int size,
            @QueryParam(Constants.CATEGORY) String category) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        @SuppressWarnings("UnusedAssignment")
        Response.ResponseBuilder builder = null;
        size = (size == 0) ? Constants.DEFAULT_SIZE : size;
        if ("IPD".equalsIgnoreCase(category)) {
            try {
                PatientList patients = service.getIPDPatientList(start, size);
                populatePatientListLinks(patients, visitDate, start, size);
                builder = Response.ok(patients);
                return builder.build();
            } catch (CustomException ex) {
                return handleException(ex, Response.Status.NOT_FOUND);
            }
        } else {
            Date date = null;
            if (visitDate == null) {
                date = new Date();
            } else {
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    date = df.parse(visitDate);
                } catch (ParseException ex) {
                    date = new Date();
                }
            }
            try {
                PatientList patients = service.getPatientList(date, start, size);
                populatePatientListLinks(patients, visitDate, start, size);
                builder = Response.ok(patients);
                return builder.build();
            } catch (CustomException ex) {
                return handleException(ex, Response.Status.NOT_FOUND);
            }
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createPatient(Patient patient) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        Patient p;
        try {
            if(patient == null || 
                    (patient.getCategory().equals(Patient.Category.IPD) 
                    && patient.getIpdDetails() == null)) {
                throw new CustomException(ErrorMsgs.PARTIAL_DATA, ErrorMsgs.INPUT_ERROR);
            }
            p = service.createPatient(patient);
            populateGetPatientLinks(p);
            builder = Response.ok(p);
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
    public Response updatePatient(@PathParam(Constants.ID_PARAM) long mrn, Patient patient) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        Patient p;
        try {
            p = service.updatePatient(mrn, patient);
            populateGetPatientLinks(p);
            builder = Response.ok(p);
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
    
/*    @DELETE
    @Path("{" + Constants.ID_PARAM + "}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deletePatient(@PathParam(Constants.ID_PARAM) long mrn) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;        
        try {
            service.deletePatient(mrn);
            builder = Response.status(Response.Status.NO_CONTENT);
//            if (filter.getNewSessionID() != null) {
//                builder.header(Constants.JSESSIONID, filter.getNewSessionID());
//            } else {
//                builder.header(Constants.JSESSIONID, sessionId);
//            }
            return builder.build();
        } catch (CustomException ex) {
            return handleException(ex, Response.Status.NOT_MODIFIED);
        }
    }*/

    @GET
    @Path(Constants.SEARCH_PATH)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchPatient(@QueryParam("param") String searchParam) {
        AuthorizationFilter filter = new AuthorizationFilter();
        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
        filter.isPermitted(sessionId, Constants.PERM_01);

        @SuppressWarnings("UnusedAssignment")
        Response.ResponseBuilder builder = null;
        try {
            PatientList patients = service.searchPatient(searchParam);
            builder = Response.ok(patients);
//        setPrivateCacheHeaders(builder, null, Constants.PATIENT_EXPIRE);
            if (filter.getNewSessionID() != null) {
                builder.header(Constants.JSESSIONID, filter.getNewSessionID());
            } else {
                builder.header(Constants.JSESSIONID, sessionId);
            }
            return builder.build();
        } catch (CustomException ex) {
            return handleException(ex, Response.Status.NOT_FOUND);
        }
    }
    
    @PUT
    @Path("{" + Constants.ID_PARAM + "}/" + Constants.IPD_PATH)
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateIpdDetails(@PathParam(Constants.ID_PARAM) long mrn, IPDDetails ipdDetails) {
//        AuthorizationFilter filter = new AuthorizationFilter();
//        String sessionId = headers.getHeaderString(Constants.JSESSIONID);
//        filter.isPermitted(sessionId, Constants.PERM_01);

        Response.ResponseBuilder builder = null;
        ipdDetails.setMrn(mrn);
        try {
            IPDDetails ipd = service.updateIpdDetails(ipdDetails);
            builder = Response.ok(ipd);
//        setPrivateCacheHeaders(builder, null, Constants.PATIENT_EXPIRE);
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

    private Response.ResponseBuilder handleConditionalRequest(long mrn) {
        EntityTag etag = null;//createETag(item.getModificationDates());
        return request.evaluatePreconditions(etag);
    }
    
    private void populateGetPatientLinks(Patient patient){
        // self
        URI selfUri = createUri(getBaseUri(), Constants.PATIENTS_PATH + "/" + patient.getMrn(), null);
        addLink(patient, selfUri.toString(), Constants.LINK_REL_SELF, "GET");
        //update
        URI editUri = createUri(getBaseUri(), Constants.PATIENTS_PATH + "/" + patient.getMrn(), null);
        addLink(patient, editUri.toString(), Constants.LINK_REL_EDIT, "PUT");
        //delete        
//        URI deleteUri = createUri(getBaseUri(), Constants.PATIENTS_PATH + "/" + patient.getMrn(), null);
//        addLink(patient, deleteUri.toString(), Constants.LINK_REL_DELETE, "DELETE");
        
        if(patient.getCategory().equals(Patient.Category.IPD) && patient.getIpdDetails() != null) {
            URI ipdEditUri = createUri(getBaseUri(), Constants.PATIENTS_PATH + "/" + patient.getMrn() 
                    + "/" + Constants.IPD_PATH, null);
        addLink(patient.getIpdDetails(), ipdEditUri.toString(), Constants.LINK_REL_EDIT, "PUT");
        }
    }
    
    private void populatePatientListLinks(PatientList list, String visitDate, int start, int size) {
        for(Patient p : list.getPatient())
            populateGetPatientLinks(p);
        if(list.getMore() > Constants.ZERO) {
            //next
            Map<String, String> nextQParams = new HashMap<String, String>();
            nextQParams.put(Constants.VISIT_DATE, visitDate);
            nextQParams.put(Constants.START_INDEX, Integer.toString(start + size));
            nextQParams.put(Constants.SIZE, Integer.toString(size));
            URI nextUri = createUri(getBaseUri(), Constants.PATIENTS_PATH, nextQParams);
            addLink(list, nextUri.toString(), Constants.LINK_REL_NEXT, "GET");
        }
        
        if(start > Constants.ZERO && start >= size) {
            //last
            Map<String, String> lastQParams = new HashMap<String, String>();
            lastQParams.put(Constants.VISIT_DATE, visitDate);
            lastQParams.put(Constants.START_INDEX, Integer.toString(start - size));
            lastQParams.put(Constants.SIZE, Integer.toString(size));
            URI nextUri = createUri(getBaseUri(), Constants.PATIENTS_PATH, lastQParams);
            addLink(list, nextUri.toString(), Constants.LINK_REL_LAST, "GET");           
        }
    }
}
