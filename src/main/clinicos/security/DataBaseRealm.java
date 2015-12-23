package app.ws.clinicos.security;

import app.ws.clinicos.model.Permission;
import app.ws.clinicos.model.Role;
import app.ws.clinicos.model.User;
import app.ws.clinicos.dao.UserDAO;
import app.ws.clinicos.utils.CustomException;
import java.util.Collection;
import java.util.HashSet;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *  As we use RDBMS to store user info, this class helps us to validate user authenticity.
 * This realm is declared in shiro.ini file.
 * @author Arjun Golabhanvi
 * @since 16 Jan, 2014
 * @version 1.0
 */
public class DataBaseRealm extends AuthorizingRealm {
    
    private final UserDAO service = new UserDAO();

    public DataBaseRealm() {
        setName("DataBaseRealm");
    }

    /**
     * This method will be called when we do login on subject. Based on the auth info returned to Shiro 
     * framework, Shiro does validate the user authenticity.
     * @param authcToken
     * @return AuthenticationInfo
     * @throws AuthenticationException 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = service.findUser(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
        } else {
            return null;
        }
    }

    /**
     * This method will be called whenever isPermitted method call on subject happens.
     * Based on info returned from this method, Shiro checks whether given user has permission 
     * to do certain operations.
     * @param principals
     * @return AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer userId = (Integer) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        User user = null;
        try {
            user = service.findUserByID(userId);
        } catch (CustomException ex) {

        }
        if (user != null) {
            for (Role role : user.getRoles()) {
                authInfo.addRole(role.getName());
                Collection<String> permissions = new HashSet<String>();
                for (Permission p : role.getPermissions()) {
                    permissions.add(p.getName());
                }
                authInfo.addStringPermissions(permissions);
            }
        }
        return authInfo;
    }
}
