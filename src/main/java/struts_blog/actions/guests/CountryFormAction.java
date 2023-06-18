package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.actions.Titleable;
import struts_blog.models.Country;
import struts_blog.models.GuestForm;

import java.io.IOException;
import java.util.List;

public class CountryFormAction extends BaseAction implements Titleable {
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

    public List<Country> getCountries() throws IOException {
        return Country.getAllCountries();
    }

    @Override
    public String getTitle() {
        return "Country Entry for Guest";
    }
}
