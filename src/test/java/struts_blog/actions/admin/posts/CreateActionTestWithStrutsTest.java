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
package struts_blog.actions.admin.posts;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.junit.StrutsTestCase;
import org.apache.struts2.util.TokenHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateActionTestWithStrutsTest extends StrutsTestCase {
    protected ActionProxy actionProxy;
    protected CreateAction action;

    public void test_path_mapping() {
        ActionMapping mapping = getActionMapping("/admin/posts/create.action");

        assertEquals("/admin/posts", mapping.getNamespace());
        assertEquals("create", mapping.getName());
    }

    public void test_execute_with_valid_parameters_returns_success() throws Exception {
        request.setParameter("post.title", "Test Title");
        request.setParameter("post.content", "Test Content");
        request.setParameter("struts.token.name", "token");
        request.setParameter("token", "mockTokenValue");

        this.actionProxy = getActionProxy("/admin/posts/create");
        actionProxy.getInvocation().getInvocationContext().withSession(new HashMap<>(Map.of("user_id", 1, "struts.tokens.token", "mockTokenValue")));

        this.action = (CreateAction) actionProxy.getAction();

        String result = actionProxy.execute();

        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_with_invalid_parameters_returns_input() throws Exception {
        request.setParameter("post.title", "");
        request.setParameter("post.content", "Test Content");

        this.actionProxy = getActionProxy("/admin/posts/create");
        // We don't need authentication information because we do not reach the Action
        String result = actionProxy.execute();

        this.action = (CreateAction) actionProxy.getAction();

        assertEquals(1, action.getFieldErrors().size());
        assertEquals("Title is required", action.getFieldErrors().get("post.title").get(0));

        assertEquals(ActionSupport.INPUT, result);
        assertEquals(200, response.getStatus());
        assertEquals("/WEB-INF/content/admin/posts/new.jsp", response.getForwardedUrl());
    }
}
