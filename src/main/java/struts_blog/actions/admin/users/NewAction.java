package struts_blog.actions.admin.users;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;

public class NewAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;

    public NewAction() {
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        return SUCCESS;
    }

    @Override
    public String getTitle() {
        return "New User";
    }
}
