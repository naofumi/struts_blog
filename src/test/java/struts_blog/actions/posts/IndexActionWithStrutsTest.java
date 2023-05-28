package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.junit.StrutsTestCase;

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
        this.actionProxy = getActionProxy("/posts");
        this.action = (IndexAction) actionProxy.getAction();

        assertNotNull(this.action);
    }

    public void test_execute_returns_success() throws Exception {
        this.actionProxy = getActionProxy("/posts");
        this.action = (IndexAction) actionProxy.getAction();

        String result = actionProxy.execute();

        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_sets_getPost() throws Exception {
        this.actionProxy = getActionProxy("/posts");
        this.action = (IndexAction) actionProxy.getAction();

        actionProxy.execute();

        assertEquals("tako", action.getPosts().get(0).getTitle());
    }

    public void test_can_execute_test_executeAction() throws Exception {
        String body = executeAction("/posts");

        // I haven't yet set it up to return JSP content
        assertEquals("", body);
        assertEquals(200, response.getStatus());
    }
}
