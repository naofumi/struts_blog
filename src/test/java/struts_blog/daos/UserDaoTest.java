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

    public void test_find_with_valid_id() {
        User result = userDao.find(1);
        assertEquals("naofumi@mac.com", result.getEmail());
    }

    public void test_find_by_email_with_existing_email() {
        User result = userDao.findBy("email", "naofumi@mac.com");
        assertEquals("naofumi@mac.com", result.getEmail());
    }

    public void test_find_by_email_with_non_existing_email() {
        User result = userDao.findBy("email", "boo@mac.com");
        assertNull(result);
    }

    public void testCreateUser() {
        User user = new User();
        user.setEmail("hello@ma.com");
        user.setPasswordDigest("passwordDigest");
        userDao.create(user);

        User result = userDao.findBy("email", "hello@ma.com");
        assertEquals("hello@ma.com", result.getEmail());
    }

    public void testCreateAndReturnSavedUser() {
        User user = new User();
        user.setEmail("test@mac.com");
        user.setPasswordDigest("hoge1234");

        User createdUser = userDao.createAndReturnSaved(user);

        assertNotNull(createdUser.getId()); // assert id was injected after creation
        User result = userDao.find(createdUser.getId()); // confirm that an entry with that Id really exists
        assertEquals("test@mac.com", result.getEmail());
    }
}
