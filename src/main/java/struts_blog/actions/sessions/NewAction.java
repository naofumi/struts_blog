package struts_blog.actions.sessions;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.models.Login;

public class NewAction extends BaseAction implements Titleable {
	private static final long serialVersionUID = 1L;
	private final Login login = new Login();

	public String execute() {
		return SUCCESS;
	}

	public Login getLogin() {
		return login;
	}

	@Override
	public String getTitle() {
		return "Login Form";
	}
}
