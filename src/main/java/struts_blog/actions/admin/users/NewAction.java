package struts_blog.actions.admin.users;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.models.Post;
import struts_blog.models.User;

public class NewAction extends AdminBaseAction {
	private static final long serialVersionUID = 1L;

	public NewAction() {
	}

	public String execute() {
		return SUCCESS;
	}
}
