package struts_blog.actions.admin.users;

import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.OneTimeTokenService;
import struts_blog.models.User;

public class UpdateAction extends AdminBaseAction {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
