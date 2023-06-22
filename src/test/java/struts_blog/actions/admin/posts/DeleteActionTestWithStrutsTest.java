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
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.junit.StrutsTestCase;
import struts_blog.setup.TestSetup;

import java.util.HashMap;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class DeleteActionTestWithStrutsTest extends StrutsTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        new TestSetup().setUpDb();
    }

    public void test_path_mapping() {
        ActionMapping mapping = getActionMapping("/admin/posts/delete.action");

        assertEquals("/admin/posts", mapping.getNamespace());
        assertEquals("delete", mapping.getName());
    }

    public void test_execute_with_valid_parameters_returns_success() throws Exception {
        request.setParameter("id", "1");
        request.setParameter("struts.token.name", "token");
        request.setParameter("token", "mockTokenValue");

        ActionProxy actionProxy = getActionProxy("/admin/posts/delete");
        actionProxy.getInvocation().getInvocationContext()
                .withSession(new HashMap<>(Map.of("user_id", 1, "struts" + ".tokens.token", "mockTokenValue")));

        String result = actionProxy.execute();
        DeleteAction action = (DeleteAction) actionProxy.getAction();

        assertEquals(SUCCESS, result);
        assertEquals(302, response.getStatus());
        assertEquals("/admin/posts/index.action", response.getHeader("Location"));
    }
}
