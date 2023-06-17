package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.Guest;
import struts_blog.setup.TestSetup;

public class GuestDaoTest extends TestCase {
    GuestDao guestDao = new GuestDao();

    public void setUp() throws Exception {
        super.setUp();
        new TestSetup().setUpDb();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        new TestSetup().setUpDb();
    }

    public void test_object_is_correctly_created() {
        Guest result = guestDao.find(1);

        assertEquals("Nickname 1", result.getNickname());
        assertEquals("Country 1", result.getCountry());
        assertEquals("Twitter 1", result.getTwitter());
    }

    public void test_fields_are_correctly_assigned_on_create() {
        Guest guest = new Guest();
        guest.setNickname("Nickname 1");
        guest.setCountry("Country 1");
        guest.setTwitter("Twitter 1");

        Guest createdGuest = guestDao.createAndReturnSaved(guest);
        Guest reloadedGuest = guestDao.find(createdGuest.getId());
        assertEquals("Nickname 1", reloadedGuest.getNickname());
        assertEquals("Country 1", reloadedGuest.getCountry());
        assertEquals("Twitter 1", reloadedGuest.getTwitter());
    }

    public void test_fields_are_correctly_assigned_on_update() {
        Guest guest = guestDao.findBy("nickname", "Nickname 1");
        guest.setNickname("New Nickname 1");
        guest.setCountry("New Country 1");
        guest.setTwitter("New Twitter 1");

        guestDao.update(guest);

        Guest reloadedGuest = guestDao.find(guest.getId());
        assertEquals("New Nickname 1", reloadedGuest.getNickname());
        assertEquals("New Country 1", reloadedGuest.getCountry());
        assertEquals("New Twitter 1", reloadedGuest.getTwitter());
    }
}
