package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.models.GuestForm;

public class CountryFormAction extends BaseAction {
    private GuestForm guestForm;

    public String execute() {
        this.guestForm = GuestForm.retrieveFromSession(sessionMap);
        if (guestForm.getNickname().isBlank()) {
            return "backToNickname";
        }

        return SUCCESS;
    }

    public GuestForm getGuestForm() {
        return guestForm;
    }
}
