package struts_blog.actions.admin.posts;

import struts_blog.actions.BaseAction;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

public class UpdateAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Post post;

	PostDao postDao = new PostDao();

	public UpdateAction() {
		// TODO Auto-generated constructor stub
	}

	public String execute() {
		if (postDao.updatePost(post)){
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
