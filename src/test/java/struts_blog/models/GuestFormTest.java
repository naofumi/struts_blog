package struts_blog.models;

import junit.framework.TestCase;

import java.util.Map;

public class GuestFormTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testCreateFromSessionWithValidSession() {
        Map<String, Object> session = Map.of("guest", Map.of("nickname", "Test Nickname"));
        GuestForm guestForm = GuestForm.createFromSession(session);

        assertEquals("Test Nickname", guestForm.getNickname());
    }

    public void testCreateFromSessionWithNullSession() {
        GuestForm guestForm = GuestForm.createFromSession(null);

        assertEquals("", guestForm.getNickname());
    }

    public void testCreateFromSessionWithEmptySession() {
        Map<String, Object> session = Map.of();
        GuestForm guestForm = GuestForm.createFromSession(session);

        assertEquals("", guestForm.getNickname());
    }

    public void testGetNickname() {
    }

    public void testSetNickname() {
    }

    public void testUpdateWithGuestFormWithSameKeysWillOverrideValues() {
        Map<String, Object> session = Map.of("guest", Map.of("nickname", "old Nickname"));
        GuestForm guestForm = GuestForm.createFromSession(session);

        GuestForm newGuestForm = new GuestForm();
        newGuestForm.setNickname("new nickname");

        guestForm.updateWithGuestForm(newGuestForm);

        assertEquals("new nickname", guestForm.getNickname());
    }

    public void testUpdateWithGuestFormWhenNewGuestFormIsEmptyWillNotChangeValues() {
        Map<String, Object> session = Map.of("guest", Map.of("nickname", "old Nickname"));
        GuestForm guestForm = GuestForm.createFromSession(session);

        GuestForm newGuestForm = new GuestForm();

        guestForm.updateWithGuestForm(newGuestForm);

        assertEquals("old Nickname", guestForm.getNickname());
    }

    public void testUpdateWithGuestFormWhenNewGuestFormHasNewKeysOnlyWillAdditivelyMergeKeys() {
        Map<String, Object> session = Map.of("guest", Map.of("nickname", "old Nickname"));
        GuestForm guestForm = GuestForm.createFromSession(session);

        GuestForm newGuestForm = new GuestForm();
        newGuestForm.setCountry("United States of China");

        guestForm.updateWithGuestForm(newGuestForm);

        assertEquals("old Nickname", guestForm.getNickname());
        assertEquals("United States of China", guestForm.getCountry());
    }

}
