package struts_blog.actions.admin.users;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.junit.StrutsTestCase;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.daos.UserDao;
import struts_blog.models.User;
import struts_blog.setup.TestSetup;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThrows;

/*
 * Here we are testing the capabilities of the `StrutsTestCase`.
 *
 * It looks similar to request testing in RSpec in that it is difficult to directly
 * access the session. Otherwise, it looks pretty nice to use.
 * */
public class EditActionWithStrutsTest extends StrutsTestCase {
    ActionProxy actionProxy;
    EditAction action;
    UserDao userDao = new UserDao();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        new TestSetup().setUpDb();

        this.actionProxy = getActionProxy("/admin/users/edit.action");
        this.action = (EditAction) actionProxy.getAction();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        new TestSetup().setUpDb();
    }

    public void test_path_routes_here() {
        assertTrue(action instanceof EditAction);
    }

    public void test_fails_if_unauthenticated() throws Exception {
        action.setId(1);

        String result = actionProxy.execute();
        assertEquals("unauthenticated", result);
        assertEquals(302, response.getStatus());
    }

    public void test_execute_returns_success_and_sets_getUser() throws Exception {
        User userToEdit = userDao.findBy("email", "spongebob@example.com");

        action.setId(userToEdit.getId());

        actionProxy.getInvocation().getInvocationContext().withSession(new HashMap<>(Map.of("user_id", 1)));

        String result = actionProxy.execute();

        assertEquals(ActionSupport.SUCCESS, result);
        assertEquals("/WEB-INF/content/admin/users/edit.jsp", response.getForwardedUrl());
        assertEquals("spongebob@example.com", action.getUser().getEmail());
    }
}
