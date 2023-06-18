package struts_blog.actions.sessions;

import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.models.Login;
import struts_blog.models.User;

import java.util.Objects;

public class CreateAction extends BaseAction implements Titleable {
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
