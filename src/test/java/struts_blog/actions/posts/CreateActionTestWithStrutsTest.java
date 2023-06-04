/*
* Here I am using struts2.junit to write tests that use a mock request.
* This allows us to test
* 1. URL to Action mapping
* 2. Interceptors
* 3. Assigment of HTTP parameters to instance variables
*
* This may also help when the Action uses the ActionContext for example,
* and we cannot run the test without a mock for that.
* */
package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.junit.StrutsTestCase;
import struts_blog.actions.admin.posts.CreateAction;

import java.util.HashMap;
import java.util.Map;

public class CreateActionTestWithStrutsTest extends StrutsTestCase {
    protected ActionProxy actionProxy;
    protected CreateAction action;

    public void test_path_maps_here() {
        this.actionProxy = getActionProxy("/admin/posts/create");

        HashMap<String, Object> mockSession = new HashMap<>();
        mockSession.put("user_id", 1);
        setSessionOnActionProxy(actionProxy, mockSession);

        assertEquals("create", actionProxy.getActionName());
        assertNotNull(actionProxy);
    }

    public void test_execute_with_valid_parameters_returns_success() throws Exception {
        request.setParameter("post.title", "Test Title");
        request.setParameter("post.content", "Test Content");

        this.actionProxy = getActionProxy("/admin/posts/create");

        HashMap<String, Object> mockSession = new HashMap<>();
        mockSession.put("user_id", 1);
        setSessionOnActionProxy(actionProxy, mockSession);

        this.action = (CreateAction) actionProxy.getAction();
        String result = actionProxy.execute();

        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_with_invalid_parameters_returns_input() throws Exception {
        request.setParameter("post.title", "");
        request.setParameter("post.content", "Test Content");

        this.actionProxy = getActionProxy("/admin/posts/create");

//        HashMap<String, Object> mockSession = new HashMap<>();
//        mockSession.put("user_id", 1);
//        setSessionOnActionProxy(actionProxy, mockSession);

        this.action = (CreateAction) actionProxy.getAction();
        String result = actionProxy.execute();

        assertEquals(1, action.getFieldErrors().size());
        assertEquals("Title is required.", action.getFieldErrors().get("post.title").get(0));

        assertEquals(ActionSupport.INPUT, result);
    }

    private void setSessionOnActionProxy(ActionProxy actionProxy, Map sessionMap) {
        //  https://stackoverflow.com/a/19653568
        actionProxy.getInvocation().getInvocationContext().setSession(sessionMap);
    }

}
