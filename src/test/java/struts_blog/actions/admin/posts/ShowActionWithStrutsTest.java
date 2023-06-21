package struts_blog.actions.admin.posts;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.junit.StrutsTestCase;
import struts_blog.models.Post;
import struts_blog.setup.TestSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowActionWithStrutsTest extends StrutsTestCase {

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

    public void test_routes() {
        ActionMapping mapping = getActionMapping("/admin/posts/show.action");

        assertEquals("/admin/posts", mapping.getNamespace());
        assertEquals("show", mapping.getName());
    }

    public void test_execute_returns_success() throws Exception {
        ActionProxy actionProxy = getActionProxy("/admin/posts/show.action");
        actionProxy.getInvocation().getInvocationContext().withSession(new HashMap<>(Map.of("user_id", 1)));
        request.setParameter("id", "1");
        String result = actionProxy.execute();

        ShowAction action = (ShowAction) actionProxy.getAction();

        assertEquals(ActionSupport.SUCCESS, result);
        assertEquals(200, response.getStatus());
        assertEquals("/WEB-INF/content/admin/posts/show.jsp", response.getForwardedUrl());
        // You can also get headers and cookies if you want to check these
    }

    public void test_execute_returns_post() throws Exception {
        request.setParameter("id", "1");
        ActionProxy actionProxy = getActionProxy("/admin/posts/show.action");
        actionProxy.getInvocation().getInvocationContext().withSession(new HashMap<>(Map.of("user_id", 1)));
        actionProxy.execute();

        ShowAction action = (ShowAction) actionProxy.getAction();

        assertEquals(1, action.getPost().getId());
        assertEquals("My first Blog Post", action.getPost().getTitle());
    }

    /*
     * As far as I have been able to discover, `executeAction` does the best
     * job of simulating an HTTP request as it goes through the Interceptor stack
     * and returns values.
     *
     * Set up the session and parameter values and then call `executeAction()`.
     * Instead of directly querying the action via getters, you can use `findValueAfterExecute()` to
     * get the state of the action.
     *
     * You can query the response object for status and JSP template. You can also get at
     * the response headers.
     *
     * However, when the response is a redirect, it tends to blow up without giving us information
     * about what the result was.
     * */
    public void test_can_execute_test_executeAction() throws Exception {
        request.getSession().setAttribute("user_id", 1);
        String body = executeAction("/admin/posts/index.action");
        ArrayList<Post> posts = (ArrayList<Post>) findValueAfterExecute("posts");// findValueAfterExecute("")

        assertEquals("My first Blog Post", posts.get(0).getTitle());
        assertEquals(200, response.getStatus());
        assertEquals("/WEB-INF/content/admin/posts/index.jsp", response.getForwardedUrl());
        // I haven't yet set it up to return JSP content
        assertEquals("", body);
    }
}
