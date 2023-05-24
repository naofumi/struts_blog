package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;
import struts_blog.models.Post;
import struts_blog.daos.PostDao;

public class CreateAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Post post;

	PostDao postDao = new PostDao();

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
