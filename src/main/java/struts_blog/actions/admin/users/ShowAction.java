package struts_blog.actions.admin.users;

import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.User;

public class ShowAction extends AdminBaseAction {
	private int id;
	private User user;
	private static final long serialVersionUID = 1L;

	UserDao userDao = new UserDao();

	public ShowAction() {
	}

	public ShowAction(UserDao userDao) {
		this.userDao = userDao;
	}

	public String execute() throws UnauthenticatedException {
		authenticate();

		setUser(userDao.find(id));

		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
