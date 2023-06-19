/*
 * Testing without using the Struts framework
 *
 * In this case, we simply extend TestCase for unit testing of the Struts Actions.
 *
 * Struts Actions are designed to be tested this way, which is apparent from how it
 * injects all the HTTP request information through setters before calling `execute()`.
 * Therefore it seems appropriate that we do as much testing as we can as Unit tests.
 *
 * An Action's role is
 *
 * 1. Receive the HTTP parameters
 * 2. Send appropriate messages to the Model based on the these HTTP parameters.
 *    This may change the state of the App (update database tables) or send external requests.
 * 3. Set an appropriate return value and the values for the getters.
 *
 * Therefore, the test's role is to
 *
 * 1. This is related to validation so this should be tested there. Since this is done
 *    in the Interceptor, it should be tested using the StrutsTestCase
 * 2. To verify that the appropriate messages were send to the Models, we could use mocks.
 *    We do need to do DI for this though.
 *    Another approach is to just check the state of the Models. This may require access to
 *    the database during testing.
 * 3. Verify the return value from the Action. Also verify the values from the getters.
 *
 *
 * */

package struts_blog.actions.admin.posts;

import com.opensymphony.xwork2.ActionSupport;
import junit.framework.TestCase;
import struts_blog.actions.BaseAction;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.models.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.junit.Assert.assertThrows;

public class IndexActionTest extends TestCase {
    private IndexAction action = new IndexAction();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_execute_returns_success() throws UnauthenticatedException {
        action.withSession(new HashMap(Map.of("user_id", 1)));
        String result = action.execute();
        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_fails_if_unauthenticated() {
        Exception exception = assertThrows(UnauthenticatedException.class, () -> {
            action.execute();
        });

        assertEquals("You must log in to access the page", exception.getMessage());
    }

    public void test_execute_sets_visitCount_to_1_on_initial_visit() throws UnauthenticatedException {
        action.withSession(new HashMap(Map.of("user_id", 1)));
        action.execute();

        assertEquals(1, action.getVisitsCount());
    }

    public void test_execute_increments_visitCount_on_subsequent_visits() throws UnauthenticatedException {
        HashMap<String, Object> previousSession = new HashMap<>(Map.ofEntries(
                entry("user_id", 1),
                entry(BaseAction.VISITS_COUNT_SESSION_KEY, 3)
        ));

        action.withSession(previousSession);
        action.execute();

        assertEquals(4, action.getVisitsCount());
    }

    public void test_execute_sets_getPost() throws UnauthenticatedException {
        action.withSession(new HashMap(Map.of("user_id", 1)));
        action.execute();

        List<Post> posts = action.getPosts();
        assertEquals("My first Blog Post", posts.get(0).getTitle());
    }
}
