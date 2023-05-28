package struts_blog.actions.posts;

import struts_blog.actions.BaseAction;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

public class CreateAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Post post;

	PostDao postDao = new PostDao();

	public CreateAction() {}

	public CreateAction(PostDao postDao) {
		this.postDao = postDao;
	}

	public String execute() {
		if (postDao.createPost(post)) {
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
