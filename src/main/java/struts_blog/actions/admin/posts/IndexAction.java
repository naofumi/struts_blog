package struts_blog.actions.admin.posts;

import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.models.PaginationLinks;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

import java.util.ArrayList;

public class IndexAction extends AdminBaseAction {

    private static final long serialVersionUID = 1L;
    private ArrayList<Post> posts;
    private int page;
    private static final int PER_PAGE = 5;

    PostDao postDao = new PostDao();

    public IndexAction() {
        this.page = 1;
    }

    public IndexAction(PostDao postDao) {
        this.page = 1;
        this.postDao = postDao;
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        incrementVisitsCount();

        this.posts = new PostDao().getAllWithPage(page, 5);

        return SUCCESS;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PaginationLinks getPaginationLinks() {
        return new PaginationLinks("/struts_blog/admin/posts/index", page, postDao.getCount(), PER_PAGE);
    }

}
