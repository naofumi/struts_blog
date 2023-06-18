package struts_blog.actions.admin.posts;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

public class EditAction extends AdminBaseAction implements Titleable {
	private int id;

	private static final long serialVersionUID = 1L;

	private Post post;

	PostDao postDao = new PostDao();

	public EditAction() {
	}

	public EditAction(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
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

	@Override
	public String getTitle() {
		return "Edit Post";
	}
}
