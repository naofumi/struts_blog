package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

public class EditAction extends ActionSupport {
	private int id;

	private static final long serialVersionUID = 1L;

	private Post post;

	PostDao postDao = new PostDao();

	public EditAction() {
	}

	public EditAction(PostDao postDao) {
		this.postDao = postDao;
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
}
