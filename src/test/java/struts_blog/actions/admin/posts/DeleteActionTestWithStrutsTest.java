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

import java.util.HashMap;
import java.util.Map;

public class DeleteActionTestWithStrutsTest extends StrutsTestCase {
    public void test_path_mapping() {
        ActionMapping mapping = getActionMapping("/admin/posts/delete.action");

        assertEquals("/admin/posts", mapping.getNamespace());
        assertEquals("delete", mapping.getName());
    }

    public void test_execute_with_valid_parameters_returns_success() throws Exception {
        request.setParameter("id", "1");

        ActionProxy actionProxy = getActionProxy("/admin/posts/delete");
        actionProxy.getInvocation().getInvocationContext().withSession(new HashMap<>(Map.of("user_id", 1)));

        String result = actionProxy.execute();
        DeleteAction action = (DeleteAction) actionProxy.getAction();

        assertEquals(ActionSupport.SUCCESS, result);
    }
}
