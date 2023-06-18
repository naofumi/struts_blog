package struts_blog.actions.admin.posts;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

public class ShowAction extends AdminBaseAction implements Titleable {
	private int id;

	private static final long serialVersionUID = 1L;

	private Post post;

	PostDao postDao = new PostDao();

	public ShowAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() throws UnauthenticatedException {
		authenticate();

		this.post = postDao.find(id);

		return SUCCESS;
	}

	public Post getPost() {
		return post;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return "Show Post";
	}
}
