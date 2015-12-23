package app.ws.clinicos.application;

import app.ws.clinicos.resources.*;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *  Configuration class which registered the resources.
 * @author Arjun Golabhanvi
 * @since 01 Jan, 2014
 * @version 1.0
 */
@ApplicationPath("/")
public class AppConfig extends Application {

    @Override
    @SuppressWarnings("unchecked")
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(PatientResource.class);
        set.add(AuthenticationResource.class);
        set.add(UserResource.class);
        set.add(DoctorResource.class);
        set.add(AppointmentResource.class);
        return set;
    }
}

