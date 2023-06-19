package struts_blog.actions.posts;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

public class ShowAction extends BaseAction implements Titleable {
    PostDao postDao = new PostDao();
    private int id;
    private Post post;

    public String execute() {
        this.post = postDao.find(id);

        return SUCCESS;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
