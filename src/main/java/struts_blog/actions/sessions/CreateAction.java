package struts_blog.actions.sessions;

import struts_blog.actions.BaseAction;
import struts_blog.models.Login;

import java.util.Objects;

public class CreateAction extends BaseAction {
    private Login login;

    public CreateAction() {
    }

    public String execute() {
        if (login.isValid()) {
            // Sessions should be invalidated prior to login
            // to prevent session fixation attacks.
            sessionMap.invalidate();
            sessionMap.put("user_id", login.getEmail());
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
}
