package struts_blog.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.services.AuthenticationService;
import struts_blog.models.User;
import struts_blog.services.FlashService;

import java.util.HashMap;
import java.util.Map;

/*
 * Features supported by the BaseAction class
 *
 * Authentication:
 * ===
 *
 * Using the `AuthenticationService` set in field `authenticationService`, this class
 * provides the `authenticate()`, `isLoggedIn()` and `currentUser()` methods to
 * check the current authentication status.
 *
 * Flash
 * ===
 *
 * Flash is a way to show a message on the next screen, but to discard it for subsequent ones.
 * It is often used when, for example, you want to display the results for the action that
 * was performed on the immediately previous action like "Your post was successfully updated"
 * or "Successfully logged in".
 *
 * */
public abstract class BaseAction extends ActionSupport implements SessionAware, AuthenticationAware {
    public String flash;
    protected Map<String, Object> sessionMap = new HashMap<String, Object>();

    User currentUser;
    AuthenticationService authenticationService = new AuthenticationService();
    FlashService flashService = new FlashService();

    public void withSession(Map<String, Object> session) {
        this.sessionMap = session;
        this.flash = flashService.prepareFlash(sessionMap);
    }

    // Dependency injection
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String message) {
        flashService.setFlash(message, sessionMap);
    }

    protected void invalidateSession() {
        if (sessionMap instanceof SessionMap) {
            ((SessionMap<String, Object>) sessionMap).invalidate();
        }
    }

    public User getCurrentUser() {
        // Memoization
        // Since authenticationService.getUserFromSession() requires access to the database,
        // it can become costly if repeated many times. To prevent this, we do "memoization"
        // which is to remember the currentUser for the duration of the current request.
        //
        // Memoization is a very powerful technique when optimising page load times.
        if (currentUser != null) {
            return currentUser;
        }

        return this.currentUser = authenticationService.getUserFromSession(sessionMap);
    }
}
