package struts_blog.models;

import java.awt.*;
import java.util.Map;

public class GuestForm {
    private String nickname = "";
    private String country = "";
    private String twitter = "";
    static private final String GUEST_KEY = "guest";

    static public GuestForm createFromSession(Map<String, Object> sessionMap) {
        GuestForm guestForm = new GuestForm();
        if (sessionMap == null) {
            // This is a null object with all values set to non-null defaults
            return new GuestForm();
        }

        guestForm.setFieldsFromSessionMap(sessionMap);
        return guestForm;
    }

    /* We want to change this implementation. Instead of saving the GuestForm as a Map in the session,
    *  we want to save it as is, which means as a GuestForm object. */
    private void setFieldsFromSessionMap(Map<String, Object> sessionMap) {
        Map<String, Object> guestFormMap = (Map<String, Object>) sessionMap.get(GUEST_KEY);
        if (guestFormMap == null) {
            return;
        }

        setNickname((String) guestFormMap.get("nickname"));
        setCountry((String) guestFormMap.get("country"));
        setTwitter((String) guestFormMap.get("country"));
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void updateWithGuestForm(GuestForm guestForm) {
        if (!guestForm.getNickname().isBlank()) {
            this.nickname = guestForm.getNickname();
        }
        if (!guestForm.getCountry().isBlank()) {
            this.country = guestForm.getCountry();
        }
        if (!guestForm.getTwitter().isBlank()) {
            this.twitter = guestForm.getTwitter();
        }
    }

    public void saveToSession(Map<String, Object> sessionMap) {
        sessionMap.put(GUEST_KEY, this);
    }
}
