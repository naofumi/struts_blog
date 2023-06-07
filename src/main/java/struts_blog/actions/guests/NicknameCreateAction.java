package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.models.GuestForm;

public class NicknameCreateAction extends BaseAction {
    private GuestForm guestForm;

    public String execute() {
        GuestForm guestFormInSession = GuestForm.createFromSession(sessionMap);
        guestFormInSession.updateWithGuestForm(guestForm);

        guestFormInSession.saveToSession(sessionMap);

        return SUCCESS;
    }

    public GuestForm getGuestForm() {
        return guestForm;
    }

    public void setGuestForm(GuestForm guestForm) {
        this.guestForm = guestForm;
    }
}
