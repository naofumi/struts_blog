package struts_blog.actions.admin.posts;

import struts_blog.actions.Titleable;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.PostDao;

public class DeleteAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;
    PostDao postDao = new PostDao();
    private int id;
    private String method;

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

    @Override
    public String getTitle() {
        return "Delete Post";
    }
}
