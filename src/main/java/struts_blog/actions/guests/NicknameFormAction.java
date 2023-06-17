package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.models.GuestForm;

public class NicknameFormAction extends BaseAction {
    private GuestForm guestForm;

    public String execute() {
        this.guestForm = GuestForm.retrieveFromSession(sessionMap);

        return SUCCESS;
    }

    public GuestForm getGuestForm() {
        return guestForm;
    }
}
