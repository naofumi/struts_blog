package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.models.GuestForm;

public class NicknameCreateAction extends BaseAction {
    private GuestForm guestForm;

    public String execute() {
        GuestForm guestFormInSession = GuestForm.createFromSession(sessionMap);
        // update the guestForm from the session with the guestForm information
        // from HTTP parameters.
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
