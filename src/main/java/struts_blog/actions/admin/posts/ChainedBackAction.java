package struts_blog.actions.admin.posts;

import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;

public class ChainedBackAction extends AdminBaseAction {

	public String execute() throws UnauthenticatedException {
		authenticate();

		return SUCCESS;
	}
}
