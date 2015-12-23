package app.ws.clinicos.utils;

/**
 *
 * @author agolabhanvi
 */
public final class ErrorMsgs {
    public static final String DB_ERROR = "DB_ERROR";
    
    public static final String INPUT_ERROR = "INPUT_ERROR";
    
    public static final String OTHER_ERROR = "OTHER_ERRORS";
    
    public static final String MRN_NOT_FOUND = "Entered MRN does not exist. Try with other parameters.";
    
    public static final String USER_NOT_FOUND = "User not found for provided ID.";
    
    public static final String DOCTOR_NOT_FOUND = "Doctor not found for provided ID.";
    
    public static final String NO_DATA = "No patient data found for given criteria.";
    
    public static final String NO_AUTH_DATA = "No authentication data.";

    public static final String INVALID_AUTH_DATA = "Incorrect authentication data.";

    public static final String INVALID_SESSION = "Can't logout an invalid session.";

    public static final String NO_SESSION_ID = "No session ID provided.";

    public static final String SESSION_EXPIRED = "Session expired.";

    public static final String UNKNOWN_ACCOUNT = "Unknown account.";

    public static final String INCORRECT_CREDS = "Incorrect credentials.";
    
    public static final String UNEXPECTED_ERROR = "Some other problem.";
    
    public static final String ADMIN_ACTIVITY_ERROR = "Admin activity needs active session.";
    
    public static final String NOT_ALLOWED = "User not allowed to perform this activity.";
    
    public static final String PARTIAL_DATA = "Incomplete data.";
}
