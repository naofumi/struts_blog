package struts_blog.actions.posts;

import struts_blog.actions.BaseAction;
import struts_blog.models.Paginatable;
import struts_blog.models.PaginationLinks;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

import java.util.ArrayList;

public class IndexAction extends BaseAction implements Paginatable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Post> posts;
    private int page;
    private static final int PER_PAGE = 5;

    PostDao postDao = new PostDao();

    public IndexAction() {
		this.page = 1;
    }

    public IndexAction(PostDao postDao) {
        this.postDao = postDao;
    }

    public String execute() {
        incrementVisitsCount();

        this.posts = postDao.getAllWithPage(page, 5);

        return SUCCESS;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    /* Paginatable interface */
    public void setPage(int page) {
		this.page = page;
    }

    @Override
    public String getBaseUrl() {
        return "/struts_blog/posts";
    }

    @Override
    public int getPage() {
        return this.page;
    }

    @Override
    public int getCount() {
        return postDao.getCount();
    }
    @Override
    public int getPerPage() {
        return PER_PAGE;
    }
    /* end of Paginatable interface */

}
