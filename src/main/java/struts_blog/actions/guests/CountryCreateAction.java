package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.models.GuestForm;

public class CountryCreateAction extends BaseAction {
    private GuestForm guestForm;

    public String execute() {
        GuestForm guestFormInSession = GuestForm.retrieveFromSession(sessionMap);
        // update the guestForm from the session with the guestForm information
        // from HTTP parameters.
        guestFormInSession.updateFromOtherGuestForm(guestForm);

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
