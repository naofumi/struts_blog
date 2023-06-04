package struts_blog.actions.admin;

import struts_blog.actions.BaseAction;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.daos.UserDao;
import struts_blog.models.AuthenticationService;
import struts_blog.models.User;

public abstract class AdminBaseAction extends BaseAction {
    User currentUser;
    AuthenticationService authenticationService = new AuthenticationService();

    public User getCurrentUser() {
        // Memoization
        if (currentUser != null)
            return currentUser;

        return this.currentUser = authenticationService.userFromSession(sessionMap);
    }

    public boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    protected void authenticate() throws UnauthenticatedException {
        if (!isLoggedIn()) {
            setFlash("You must be logged in!");
            throw new UnauthenticatedException("You must log in to access the page");
        };
    }
}
