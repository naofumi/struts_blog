package struts_blog.actions.admin.users;

import junit.framework.TestCase;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.daos.UserDao;
import struts_blog.models.User;
import struts_blog.setup.TestSetup;

import java.util.HashMap;
import java.util.Map;

public class UpdateActionTest extends TestCase {
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

    public void test_user_updated_with_valid_parameters() throws UnauthenticatedException {
        UpdateAction action = new UpdateAction();

        // Authentication
        action.withSession(new HashMap<>(Map.of("user_id", 1)));

        User user = userDao.findBy("email", "spongebob@example.com");
        user.setEmail("newEmail@example.com");

        action.setUser(user);

        String result = action.execute();

        assertEquals("success", result);
        User reloadedUser = userDao.find(user.getId());
        assertEquals("newEmail@example.com", reloadedUser.getEmail());
    }
}
