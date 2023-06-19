package struts_blog.actions.admin.posts;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

public class UpdateAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;
    PostDao postDao = new PostDao();
    private Post post;

    public String execute() throws UnauthenticatedException {
        authenticate();

        if (postDao.update(post)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String getTitle() {
        return "Update Post";
    }
}
