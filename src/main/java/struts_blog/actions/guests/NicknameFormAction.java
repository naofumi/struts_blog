package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.models.GuestForm;

public class NicknameFormAction extends BaseAction implements Titleable {
    private GuestForm guestForm;

    public String execute() {
        this.guestForm = GuestForm.retrieveFromSession(sessionMap);

        return SUCCESS;
    }

    public GuestForm getGuestForm() {
        return guestForm;
    }

    @Override
    public String getTitle() {
        return "Set Nickname for Guest";
    }
}
