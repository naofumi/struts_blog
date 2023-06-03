package struts_blog.actions.admin.users;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.PostDao;
import struts_blog.daos.UserDao;
import struts_blog.models.Post;
import struts_blog.models.User;

public class ShowAction extends AdminBaseAction {
	private int id;

	private static final long serialVersionUID = 1L;

	UserDao userDao = new UserDao();

	public ShowAction() {
	}

	public ShowAction(UserDao userDao) {
		this.userDao = userDao;
	}

	public String execute() {
		setUser(userDao.find(id));

		return SUCCESS;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
