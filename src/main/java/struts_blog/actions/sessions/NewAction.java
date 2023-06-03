package struts_blog.actions.sessions;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.models.Login;

public class NewAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private final Login login = new Login();

	public String execute() {
		return SUCCESS;
	}

	public Login getLogin() {
		return login;
	}
}
