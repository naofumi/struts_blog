package struts_blog.actions.about;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;

public class ShowAction extends BaseAction implements Titleable {
    public String execute() {
        return SUCCESS;
    }

    @Override
    public String getTitle() {
        return "About";
    }
}
