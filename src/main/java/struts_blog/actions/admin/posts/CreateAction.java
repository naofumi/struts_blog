package struts_blog.actions.admin.posts;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

public class CreateAction extends AdminBaseAction implements Titleable {
    PostDao postDao = new PostDao();
    private Post post;

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        this.post = postDao.createAndReturnSaved(post);

        return SUCCESS;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String getTitle() {
        return "Create Post";
    }
}
