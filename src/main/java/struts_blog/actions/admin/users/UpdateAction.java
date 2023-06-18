package struts_blog.actions.admin.users;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.User;

public class UpdateAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;
    private User user;

    UserDao userDao = new UserDao();

    public String execute() throws UnauthenticatedException {
        authenticate();

        if (userDao.update(user)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    @Override
    public void validate() {
        User user = getUser();
        if (user.getPassword().isBlank() && user.getPasswordConfirm().isBlank()) {
            return;
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            addFieldError("user.password","Password and Password Confirmation must match");
            addFieldError("user.passwordConfirm","Password and Password Confirmation must match");
        }
        if (user.getPassword().length() < 6) {
            addFieldError("user.password","You password needs to be a minimum of 6 letters");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getTitle() {
        return "Update User";
    }
}
