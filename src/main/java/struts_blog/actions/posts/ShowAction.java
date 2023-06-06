package struts_blog.actions.posts;

import struts_blog.actions.BaseAction;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

public class ShowAction extends BaseAction {
    private int id;
    private Post post;

    PostDao postDao = new PostDao();

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
}