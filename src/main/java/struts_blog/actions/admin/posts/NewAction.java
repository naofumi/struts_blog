package struts_blog.actions.admin.posts;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.models.Post;

public class NewAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;

    private Post post;

    public NewAction() {
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        this.post = new Post();

        return SUCCESS;
    }

    public Post getPost() {
        return post;
    }

    @Override
    public String getTitle() {
        return "New Post";
    }
}
