package struts_blog.actions.sessions;

import junit.framework.TestCase;
import struts_blog.daos.UserDao;
import struts_blog.models.Login;
import struts_blog.models.User;
import struts_blog.setup.TestSetup;

import java.util.HashMap;

public class CreateActionTest extends TestCase {
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

    public void test_success_and_sets_user_id_in_session_when_valid_credentials() {
        CreateAction action = new CreateAction();

        User user = userDao.findBy("email", "naofumi@mac.com");

        Login login = new Login();
        login.setEmail(user.getEmail());
        login.setPassword("password"); // user's password is "password"
        action.setLogin(login);

        HashMap<String, Object> sessionMapSpy = new HashMap<>();
        action.withSession(sessionMapSpy);

        assertEquals("success", action.execute());
        assertEquals(user.getId(), sessionMapSpy.get("user_id"));
    }

    public void test_fails_when_invalid_credentials_and_doesnt_set_session() {
        CreateAction action = new CreateAction();

        User user = userDao.findBy("email", "naofumi@mac.com");

        Login login = new Login();
        login.setEmail(user.getEmail());
        login.setPassword("badpassword"); // user's password is "password"
        action.setLogin(login);

        HashMap<String, Object> sessionMapSpy = new HashMap<>();
        action.withSession(sessionMapSpy);

        assertEquals("input", action.execute());
        assertNull(sessionMapSpy.get("user_id"));
    }

}
