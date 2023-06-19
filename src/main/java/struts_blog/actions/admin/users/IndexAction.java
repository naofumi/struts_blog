package struts_blog.actions.admin.users;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.PaginationLinks;
import struts_blog.models.User;

import java.util.ArrayList;

public class IndexAction extends AdminBaseAction implements Titleable {

    private static final long serialVersionUID = 1L;
    private static final int PER_PAGE = 5;
    UserDao userDao = new UserDao();
    private ArrayList<User> users;
    private int page;

    public IndexAction() {
        this.page = 1;
    }

    public IndexAction(UserDao userDao) {
        this.userDao = userDao;
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        this.users = userDao.getAllWithPage(page, 5);

        return SUCCESS;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PaginationLinks getPaginationLinks() {
        return new PaginationLinks("/struts_blog/admin/users/index.action", page, userDao.getCount(), PER_PAGE);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public String getTitle() {
        return "List of Users";
    }
}
