package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.User;
import struts_blog.setup.TestSetup;

import java.util.ArrayList;

public class DaoBaseTest extends TestCase {
    // To test the methods in abstract DaoBase, we will use userDao instead
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

    public void test_getAll() {
        ArrayList<User> users = userDao.getAll();
        assertEquals(2, users.size());
        assertEquals("naofumi@mac.com", users.get(0).getEmail());
    }

    public void test_getCount() {
        int count = userDao.getCount();
        assertEquals(2, count);
    }

    public void test_getAllWithLimit() {
        ArrayList<User> usersWhenLimitOne = userDao.getAllWithLimit(1);
        assertEquals(1, usersWhenLimitOne.size());

        ArrayList<User> usersWhenLimitTwo = userDao.getAllWithLimit(2);
        assertEquals(2, usersWhenLimitTwo.size());

    }

    public void test_getAllWithPage() {
        ArrayList<User> usersPageOne = userDao.getAllWithPage(1, 1);
        assertEquals(1, usersPageOne.size());
        assertEquals("naofumi@mac.com", usersPageOne.get(0).getEmail());

        ArrayList<User> usersPageTwo = userDao.getAllWithPage(2, 1);
        assertEquals(1, usersPageTwo.size());
        assertEquals("spongebob@example.com", usersPageTwo.get(0).getEmail());

        ArrayList<User> usersPageThree = userDao.getAllWithPage(3, 1);
        assertEquals(0, usersPageThree.size());
    }

    public void test_find_with_valid_id() {
        User result = userDao.find(1);
        assertEquals("naofumi@mac.com", result.getEmail());
    }

    public void test_findByEmail_with_existing_email() {
        User result = userDao.findBy("email", "naofumi@mac.com");
        assertEquals("naofumi@mac.com", result.getEmail());
    }

    public void test_findByEmail_with_non_existing_email() {
        User result = userDao.findBy("email", "boo@mac.com");
        assertNull(result);
    }

    public void test_delete() {
        User user = userDao.findBy("email", "spongebob@example.com");
        assertNotNull(user);

        userDao.delete(user.getId());
        User userReloaded = userDao.findBy("email", "spongebob@example.com");
        assertNull(userReloaded);
    }

    public void test_Create_inserts_new_user_into_database() {
        User user = new User();
        user.setEmail("hello@ma.com");
        user.setPasswordDigest("passwordDigest");
        userDao.create(user);

        User result = userDao.findBy("email", "hello@ma.com");
        assertEquals("passwordDigest", result.getPasswordDigest());
    }

    public void test_Create_returns_true_on_success() {
        User user = new User();
        user.setEmail("hello@ma.com");
        user.setPasswordDigest("passwordDigest");

        assertTrue(userDao.create(user));
    }

    public void test_CreateAndReturnSaved_inserts_new_user_and_sets_the_id() {
        User user = new User();
        user.setEmail("test@mac.com");
        user.setPasswordDigest("hoge1234");

        User createdUser = userDao.createAndReturnSaved(user);

        assertNotNull(createdUser.getId()); // assert id was injected after creation
        User result = userDao.find(createdUser.getId()); // confirm that an entry with that Id really exists
        assertEquals("test@mac.com", result.getEmail());
    }

    public void test_update() {
        User user = userDao.findBy("email", "spongebob@example.com");
        user.setEmail("patrick@example.com");
        boolean isSuccess = userDao.update(user);

        User userReloaded = userDao.find(user.getId());
        assertEquals("patrick@example.com", userReloaded.getEmail());
        assertTrue(isSuccess);
    }

    public void test_update_with_invalid_id_returns_false_and_does_not_create_entry() {
        User user = new User();
        user.setId(999);
        user.setEmail("userwithinvalidid@bad.com");
        user.setPasswordDigest("bad_password");

        boolean isSuccess = userDao.update(user);
        assertFalse(isSuccess);

        User userReloaded = userDao.find(user.getId());
        assertNull(userReloaded);
    }
}
