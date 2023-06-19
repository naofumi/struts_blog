package struts_blog.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.models.AuthenticationService;
import struts_blog.models.User;

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
    public final static String VISITS_COUNT_SESSION_KEY = "vcsk";
    public String flash;
    protected Map<String, Object> sessionMap = new HashMap<String, Object>();

    User currentUser;
    AuthenticationService authenticationService = new AuthenticationService();

    public void withSession(Map<String, Object> session) {
        this.sessionMap = session;
        prepareFlash();
    }

    public int getVisitsCount() {
        Integer visitsCount = (Integer) sessionMap.get(VISITS_COUNT_SESSION_KEY);
        if (visitsCount == null) return 0;

        return visitsCount;
    }

    protected void incrementVisitsCount() {
        int visitsCount = getVisitsCount();

        sessionMap.put(VISITS_COUNT_SESSION_KEY, visitsCount + 1);
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        sessionMap.put("flash", flash);
    }

    protected void invalidateSession() {
        if (sessionMap instanceof SessionMap) {
            ((SessionMap<String, Object>) sessionMap).invalidate();
        }
    }

    private void prepareFlash() {
        String value = (String) sessionMap.get("flash");
        if ((value != null) && (value.length() > 0)) {
            this.flash = value;
        }
        sessionMap.remove("flash");
    }

    // Dependency injection
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public User getCurrentUser() {
        // Memoization
        if (currentUser != null) {
            return currentUser;
        }

        return this.currentUser = authenticationService.userFromSession(sessionMap);
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public void authenticate() throws UnauthenticatedException {
        if (!isLoggedIn()) {
            setFlash("You must be logged in!");
            throw new UnauthenticatedException("You must log in to access the page");
        }
    }
}
