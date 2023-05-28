package struts_blog.actions.posts;

import struts_blog.actions.BaseAction;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

import java.util.ArrayList;

public class IndexAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ArrayList<Post> posts;

	PostDao postDao = new PostDao();

	public IndexAction() {
	}

	public IndexAction(PostDao postDao) {
		this.postDao = postDao;
	}

	public String execute() {
		incrementVisitsCount();
		this.posts = postDao.getPosts();
		return SUCCESS;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}
}
