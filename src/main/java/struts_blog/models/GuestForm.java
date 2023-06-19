package struts_blog.models;

import java.util.Map;

public class GuestForm {
    static private final String GUEST_KEY = "guest";
    private String nickname = "";
    private String country = "";
    private String twitter = "";

    static public GuestForm retrieveFromSession(Map<String, Object> sessionMap) {
        if (sessionMap != null && sessionMap.get("guest") != null) {
            return (GuestForm) sessionMap.get("guest");
        } else {
            return new GuestForm();
        }
    }

    public void updateFromOtherGuestForm(GuestForm guestForm) {
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

}
