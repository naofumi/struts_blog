package struts_blog.actions.users;

import struts_blog.actions.BaseAction;
import struts_blog.daos.PostDao;
import struts_blog.daos.UserDao;
import struts_blog.models.PaginationLinks;
import struts_blog.models.Post;
import struts_blog.models.User;

import java.util.ArrayList;

public class IndexAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    private static final int PER_PAGE = 5;

    private ArrayList<User> users;
    private int page;
    private int maxPages;

    UserDao userDao = new UserDao();

    public IndexAction() {
		this.page = 1;
    }

    public IndexAction(UserDao userDao) {
        this.userDao = userDao;
    }

    public String execute() {
        this.users = userDao.getAllWithPage(page, 5);
        this.maxPages = (userDao.getCount() / PER_PAGE) + 1;

        return SUCCESS;
    }

    public void setPage(int page) {
		this.page = page;
    }

    public PaginationLinks getPaginationLinks() {
        return new PaginationLinks("/struts_blog/users/index", page, maxPages);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
