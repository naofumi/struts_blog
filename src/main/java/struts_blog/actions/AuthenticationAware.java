package struts_blog.actions;

import struts_blog.models.User;
/*
* Note that these methods will often be called from the JSP files so they need to be public.
* */
public interface AuthenticationAware {
    public User getCurrentUser();
    public boolean isLoggedIn();
    public void authenticate() throws UnauthenticatedException;
}
