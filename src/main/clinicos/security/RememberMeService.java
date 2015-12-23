package app.ws.clinicos.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class has been designed to remember the users who've logged in with remember me option as true.
 * As our communication will be always based on sessionId, this class helps us to remember user even after
 * session is expired and until he logs out. And it makes sure that always there will latest sessionId associated
 * with user.
 * @author Arjun Golabhanvi
 * @since 16 Jan, 2014
 * @version 1.0
 */
public final class RememberMeService {
    private static final Map<String, String> usersVsSessions = new ConcurrentHashMap<String, String>();
    private static final Map<String, String> sessionsVsUsers = new ConcurrentHashMap<String, String>();

    /**
     * Remembers a user against given sessionId & makes sure only a single session associated with user.
     * @param user
     * @param sessionID 
     */
    synchronized public static void rememberMe(String user, String sessionID) {
        if (usersVsSessions.get(user) != null) {
            sessionsVsUsers.remove(usersVsSessions.get(user));
        }
        sessionsVsUsers.put(sessionID, user);
        usersVsSessions.put(user, sessionID);
    }

    /**
     * Forgets the user when he logs out of the system.
     * @param sessionID 
     */
    synchronized public static void forgetMe(String sessionID) {
        if (sessionID != null) {
            sessionsVsUsers.remove(sessionID);
        }
    }

    /**
     * Returns true if user is remembered else false.
     * @param sessionID
     * @return true/false
     */
    public static boolean isRemembered(String sessionID) {
        return sessionID != null ? sessionsVsUsers.containsKey(sessionID) : false;
    }
    
    /**
     * Returns user login string for given sessionID if remembered.
     * @param sessionID
     * @return user
     */
    public static String getRememberedUser(String sessionID) {
        return sessionID != null ? sessionsVsUsers.get(sessionID) : null;
    }    
}
