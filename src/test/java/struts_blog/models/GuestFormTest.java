package struts_blog.models;

import junit.framework.TestCase;

import java.util.Map;

public class GuestFormTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {
    }

    public void test_RetrieveFromSession_with_valid_session() {
        GuestForm guestFormInSession = new GuestForm();
        guestFormInSession.setNickname("Test Nickname");
        Map<String, Object> session = Map.of("guest", guestFormInSession);

        GuestForm guestForm = GuestForm.retrieveFromSession(session);

        assertEquals("Test Nickname", guestForm.getNickname());
    }

    public void testCreateFromSessionWithNullSession() {
        GuestForm guestForm = GuestForm.retrieveFromSession(null);

        assertEquals("", guestForm.getNickname());
    }

    public void testCreateFromSessionWithEmptySession() {
        Map<String, Object> session = Map.of();
        GuestForm guestForm = GuestForm.retrieveFromSession(session);

        assertEquals("", guestForm.getNickname());
    }

    public void testGetNickname() {
    }

    public void testSetNickname() {
    }

    public void testUpdateWithGuestFormWithSameKeysWillOverrideValues() {
        GuestForm guestFormInSession = new GuestForm();
        guestFormInSession.setNickname("old Nickname");
        GuestForm guestForm = GuestForm.retrieveFromSession(Map.of("guest", guestFormInSession));

        GuestForm newGuestForm = new GuestForm();
        newGuestForm.setNickname("new nickname");

        guestForm.updateFromOtherGuestForm(newGuestForm);

        assertEquals("new nickname", guestForm.getNickname());
    }

    public void testUpdateWithGuestFormWhenNewGuestFormIsEmptyWillNotChangeValues() {
        GuestForm guestFormInSession = new GuestForm();
        guestFormInSession.setNickname("old Nickname");
        Map<String, Object> session = Map.of("guest", guestFormInSession);
        GuestForm guestForm = GuestForm.retrieveFromSession(session);

        GuestForm newGuestForm = new GuestForm();

        guestForm.updateFromOtherGuestForm(newGuestForm);

        assertEquals("old Nickname", guestForm.getNickname());
    }

    public void testUpdateWithGuestFormWhenNewGuestFormHasNewKeysOnlyWillAdditivelyMergeKeys() {
        GuestForm guestFormFromSession = new GuestForm();
        guestFormFromSession.setNickname("old Nickname");
        Map<String, Object> session = Map.of("guest", guestFormFromSession);
        GuestForm guestForm = GuestForm.retrieveFromSession(session);

        GuestForm newGuestForm = new GuestForm();
        newGuestForm.setCountry("United States of China");

        guestForm.updateFromOtherGuestForm(newGuestForm);

        assertEquals("old Nickname", guestForm.getNickname());
        assertEquals("United States of China", guestForm.getCountry());
    }
}
