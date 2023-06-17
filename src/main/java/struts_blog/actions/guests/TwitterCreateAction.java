package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.models.GuestForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitterCreateAction extends BaseAction {
    private GuestForm guestForm;

    public void validate() {
        if (getGuestForm().getTwitter().isBlank()) {
            addFieldError("guestForm.twitter", "Your Twitter name is required.");
            return;
        }
        Pattern pattern = Pattern.compile("^@.*$");
        Matcher matcher = pattern.matcher(getGuestForm().getTwitter());
        if (!matcher.find()) {
            addFieldError("guestForm.twitter", "Your Twitter name needs to start with @.");
        }
    }

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
