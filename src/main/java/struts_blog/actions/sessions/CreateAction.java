package struts_blog.actions.sessions;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.models.Login;
import struts_blog.models.User;

public class CreateAction extends BaseAction implements Titleable {
    /*
     * The reason what we use a Login model is because I
     * thought it might be a good idea to back the Action with a
     * separate model (instead of having setters/getters in the Action).
     *
     * I don't necessarily think that this is a good idea, but I'm
     * experimenting ;)
     * */
    private Login login;

    public String execute() {
        User user = login.getAuthenticatedUser();
        if (user != null) {
            // Sessions should be invalidated prior to login
            // to prevent session fixation attacks.
            invalidateSession();

            sessionMap.put("user_id", user.getId());
            return SUCCESS;
        } else {
            addFieldError("login.email", "Either email or password is invalid.");
            addFieldError("login.password", "Either email or password is invalid.");
            return INPUT;
        }
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String getTitle() {
        return "Logging in...";
    }
}
