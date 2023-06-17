package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.User;
import struts_blog.setup.TestSetup;

public class UserDaoTest extends TestCase {
    UserDao userDao = new UserDao();

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
        User result = userDao.find(1);
        assertEquals("naofumi@mac.com", result.getEmail());
        assertNotNull(result.getPasswordDigest());
    }

    public void test_fields_are_correctly_assigned_on_create() {
        User user = new User();
        user.setEmail("NewTestEmail@example.com");
        user.setPasswordDigest("testPassword");

        User createdUser = userDao.createAndReturnSaved(user);
        User reloadedUser = userDao.find(createdUser.getId());
        assertEquals("NewTestEmail@example.com", reloadedUser.getEmail());
        assertEquals("testPassword", reloadedUser.getPasswordDigest());
    }

    public void test_fields_are_correctly_assigned_on_update() {
        User user = userDao.findBy("email", "spongebob@example.com");
        user.setEmail("NewTestEmail@example.com");
        user.setPasswordDigest("testPassword");

        userDao.update(user);

        User reloadedUser = userDao.find(user.getId());
        assertEquals("NewTestEmail@example.com", reloadedUser.getEmail());
        assertEquals("testPassword", reloadedUser.getPasswordDigest());
    }
}
