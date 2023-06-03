package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.daos.PostDao;

public class DeleteAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private int id;
	private String method;
	PostDao postDao = new PostDao();

	public String execute() {
		if (postDao.delete(id)) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public void setId(int id) {
		this.id = id;
	}
}
