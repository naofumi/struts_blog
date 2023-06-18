package struts_blog.models;

import junit.framework.TestCase;
import struts_blog.daos.UserDao;
import struts_blog.setup.TestSetup;

public class UserTest extends TestCase {
    private UserDao userDao = new UserDao();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        new TestSetup().setUpDb();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        new TestSetup().setUpDb();
    }

    public void test_saved_user_can_match_password_but_cannot_recall_password_itself() {
        User user = new User();
        user.setEmail("testUser@example.com");
        user.setPassword("testPassword");
        user = userDao.createAndReturnSaved(user);

        User reloadedUser = userDao.find(user.getId());
        assertEquals("testUser@example.com", reloadedUser.getEmail());
        assertNull(reloadedUser.getPassword());
        assertTrue(user.isMatchingPassword("testPassword"));

        // Confirm that we are saving the password as a digest and not as clear text
        assertNotSame("testPassword", reloadedUser.getPasswordDigest());
    }
}
