package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;

import struts_blog.models.Post;
import struts_blog.models.PostDao;

public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Post[] posts;

	PostDao postDao = new PostDao();

	public IndexAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		this.posts = postDao.getPosts();

		return SUCCESS;
	}

	public Post[] getPosts() {
		return posts;
	}
}
