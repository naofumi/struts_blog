package struts_blog.actions;

import struts_blog.daos.PostDao;
import struts_blog.models.Post;

import java.util.ArrayList;

public class IndexAction extends BaseAction {
    ArrayList<Post> posts;
    PostDao postDao = new PostDao();

    public IndexAction() {
    }

    public IndexAction(PostDao postDao) {
        this.postDao = postDao;
    }

    public String execute() {
        this.posts = postDao.getAllWithLimit(3);
        return SUCCESS;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
}


