package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.junit.StrutsTestCase;
import struts_blog.actions.admin.posts.IndexAction;

import java.util.HashMap;
import java.util.Map;

/*
* Here we are testing the capabilities of the `StrutsTestCase`.
*
* It looks similar to request testing in RSpec in that it is difficult to directly
* access the session. Otherwise, it looks pretty nice to use.
* */
public class IndexActionWithStrutsTest extends StrutsTestCase {
    ActionProxy actionProxy;
    IndexAction action;

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_path_routes_here() {
        this.actionProxy = getActionProxy("/admin/posts/index.action");
        this.action = (IndexAction) actionProxy.getAction();

        assertNotNull(this.action);
    }

    public void test_execute_returns_success() throws Exception {
        this.actionProxy = getActionProxy("/admin/posts/index.action");
        this.action = (IndexAction) actionProxy.getAction();

        HashMap<String, Object> mockSession = new HashMap<>();
        mockSession.put("user_id", 1);
        setSessionOnActionProxy(actionProxy, mockSession);

        String result = actionProxy.execute();

        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_sets_getPost() throws Exception {
        // Setting the session via the request field doesn't seem to work
        // when using actionProxy.execute()
        this.actionProxy = getActionProxy("/admin/posts/index.action");

        HashMap<String, Object> mockSession = new HashMap<>();
        mockSession.put("user_id", 1);
        setSessionOnActionProxy(actionProxy, mockSession);

        actionProxy.execute();

        this.action = (IndexAction) actionProxy.getAction();

        assertEquals("My first Blog Post", action.getPosts().get(0).getTitle());
    }

    public void test_can_execute_test_executeAction() throws Exception {
        request.getSession().setAttribute("user_id", 1);
        String body = executeAction("/admin/posts/index.action");

        // I haven't yet set it up to return JSP content
        assertEquals("", body);
        assertEquals(200, response.getStatus());
    }

    private void setSessionOnActionProxy(ActionProxy actionProxy, Map sessionMap) {
        //  https://stackoverflow.com/a/19653568
        actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
    }
}
