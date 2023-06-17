package struts_blog.actions.sessions;

import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.actions.BaseAction;

public class DeleteAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    public String execute() {
        invalidateSession();

        return SUCCESS;
    }
}
