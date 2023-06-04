package struts_blog.actions.sessions;

import struts_blog.actions.BaseAction;
import struts_blog.models.Login;

public class NewAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final Login login = new Login();

	public String execute() {
		return SUCCESS;
	}

	public Login getLogin() {
		return login;
	}
}
