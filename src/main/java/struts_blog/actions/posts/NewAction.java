package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.models.Post;

public class NewAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private Post post;

	public NewAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		this.post = new Post();

		return SUCCESS;
	}

	public Post getPost() {
		return post;
	}
}
