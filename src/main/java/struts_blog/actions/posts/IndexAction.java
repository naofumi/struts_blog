package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;

import struts_blog.models.Post;
import struts_blog.daos.PostDao;

import java.util.ArrayList;

public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private ArrayList<Post> posts;

	PostDao postDao = new PostDao();

	public IndexAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		this.posts = postDao.getPosts();
		return SUCCESS;
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}
}
