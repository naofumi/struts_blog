package struts_blog.actions.sessions;

import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;

public class DeleteAction extends BaseAction implements Titleable {
    private static final long serialVersionUID = 1L;

    public String execute() {
        invalidateSession();

        return SUCCESS;
    }

    @Override
    public String getTitle() {
        return "Logging out...";
    }
}
