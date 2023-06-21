package struts_blog.actions.admin.users;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.User;

public class EditAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;
    UserDao userDao = new UserDao();
    private int id;
    private User user;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        setUser(userDao.find(id));

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return "Edit User";
    }
}
