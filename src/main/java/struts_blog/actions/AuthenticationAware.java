package struts_blog.actions;

import struts_blog.models.User;

/*
 * Note that these methods will often be called from the JSP files so they need to be public.
 * */
public interface AuthenticationAware {
    public User getCurrentUser();
    void setFlash(String s);

    public default boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public default void authenticate() throws UnauthenticatedException {
        if (!isLoggedIn()) {
            setFlash("You must be logged in!");
            throw new UnauthenticatedException("You must log in to access the page");
        }
    }
}
