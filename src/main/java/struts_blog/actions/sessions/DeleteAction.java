package struts_blog.actions.sessions;

import struts_blog.actions.BaseAction;

public class DeleteAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    public String execute() {
        sessionMap.invalidate();

        return SUCCESS;
    }
}
