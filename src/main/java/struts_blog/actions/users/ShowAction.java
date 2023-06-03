package struts_blog.actions.users;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.daos.PostDao;
import struts_blog.daos.UserDao;
import struts_blog.models.Post;
import struts_blog.models.User;

public class ShowAction extends ActionSupport {
	private int id;

	private static final long serialVersionUID = 1L;

	private User user;

	UserDao userDao = new UserDao();

	public ShowAction() {
	}

	public ShowAction(UserDao userDao) {
		this.userDao = userDao;
	}

	public String execute() {
		this.user = userDao.find(id);

		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
