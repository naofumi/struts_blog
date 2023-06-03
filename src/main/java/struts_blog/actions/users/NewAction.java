package struts_blog.actions.users;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.models.Post;
import struts_blog.models.User;

public class NewAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private User user;

	public NewAction() {
	}

	public String execute() {
		this.user = new User();

		return SUCCESS;
	}

	public User getUser() {
		return user;
	}
}
