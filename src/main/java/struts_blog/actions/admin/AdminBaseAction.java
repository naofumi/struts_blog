package struts_blog.actions.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.actions.BaseAction;
import struts_blog.models.User;

import java.util.Map;

public abstract class AdminBaseAction extends BaseAction {
    private User user;

    public User getUser() {
        return user;
    }

    protected void setUser(User user) {
        this.user = user;
    }
    public boolean isLoggedIn() {
        return getUser() != null;
    }

}
