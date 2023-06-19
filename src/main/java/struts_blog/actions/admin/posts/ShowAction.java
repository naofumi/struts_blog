package struts_blog.actions.admin.posts;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

public class ShowAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;
    PostDao postDao = new PostDao();
    private int id;
    private Post post;

    public ShowAction() {
        // TODO Auto-generated constructor stub
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        this.post = postDao.find(id);

        return SUCCESS;
    }

    public Post getPost() {
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return "Show Post";
    }
}
