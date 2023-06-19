package struts_blog.actions.admin.users;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.junit.StrutsTestCase;
import struts_blog.daos.UserDao;
import struts_blog.models.User;
import struts_blog.setup.TestSetup;

import java.util.HashMap;
import java.util.Map;

/*
 * Here we are testing the capabilities of the `StrutsTestCase`.
 *
 * It looks similar to request testing in RSpec in that it is difficult to directly
 * access the session. Otherwise, it looks pretty nice to use.
 * */
public class EditActionWithStrutsTest extends StrutsTestCase {
    ActionProxy actionProxy;
    UserDao userDao = new UserDao();

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

    public void test_path_routes_here() {
        this.actionProxy = getActionProxy("/admin/users/edit.action");

        assertTrue(actionProxy.getAction() instanceof EditAction);
    }

    public void test_execute_returns_success_and_sets_getUser() throws Exception {
        User userToEdit = userDao.findBy("email", "spongebob@example.com");

        this.actionProxy = getActionProxy("/admin/users/edit.action");
        EditAction action = (EditAction) actionProxy.getAction();
        action.setId(userToEdit.getId());

        actionProxy.getInvocation().getInvocationContext().setSession(new HashMap(Map.of("user_id", 1)));

        String result = actionProxy.execute();

        assertEquals(ActionSupport.SUCCESS, result);
        assertEquals("/WEB-INF/content/admin/users/edit.jsp", response.getForwardedUrl());
        assertEquals("spongebob@example.com", action.getUser().getEmail());
    }
}
