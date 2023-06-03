package struts_blog.actions.admin.posts;

import struts_blog.actions.BaseAction;
import struts_blog.models.Post;

public class NewAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Post post;

	public NewAction() {
	}

	public String execute() {
		this.post = new Post();

		return SUCCESS;
	}

	public Post getPost() {
		return post;
	}
}
