package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.models.GuestForm;

public class TwitterFormAction extends BaseAction implements Titleable {
    private GuestForm guestForm;

    public String execute() {
        this.guestForm = GuestForm.retrieveFromSession(sessionMap);

        if (guestForm.getNickname().isBlank()) {
            return "backToNickname";
        } else if (guestForm.getCountry().isBlank()) {
            return "backToCountry";
        }

        return SUCCESS;
    }

    public GuestForm getGuestForm() {
        return guestForm;
    }

    @Override
    public String getTitle() {
        return "Set Twitter for Guest";
    }
}
