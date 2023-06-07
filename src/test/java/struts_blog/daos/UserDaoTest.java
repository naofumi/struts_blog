package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.User;
import struts_blog.setup.DbSetup;

public class UserDaoTest extends TestCase {
    UserDao userDao = new UserDao();

    public void setUp() throws Exception {
        super.setUp();
        new DbSetup().setUpDb();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        new DbSetup().setUpDb();
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

    public void createAndReturnSavedUser() {
        User user = new User();
        user.setEmail("test@mac.com");
        user.setPasswordDigest("hoge1234");

        User createdUser = userDao.createAndReturnSaved(user);
        System.out.println(createdUser.getId());
    }
}
