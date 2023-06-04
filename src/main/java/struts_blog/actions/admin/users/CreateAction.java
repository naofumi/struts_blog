package struts_blog.actions.admin.users;

import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.User;

public class CreateAction extends AdminBaseAction {
    private static final long serialVersionUID = 1L;
    private User user;

    UserDao userDao = new UserDao();

    public CreateAction() {
    }

    public CreateAction(UserDao userDao) {
        this.userDao = userDao;
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        this.user = userDao.createAndReturnSaved(user);

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
