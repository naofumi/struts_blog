package struts_blog.actions.admin.posts;

import struts_blog.actions.BaseAction;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

public class ShowAction extends BaseAction {
	private int id;

	private static final long serialVersionUID = 1L;

	private Post post;

	PostDao postDao = new PostDao();

	public ShowAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
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
}
