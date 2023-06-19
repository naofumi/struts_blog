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
import org.mockito.Mockito;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;

public class IndexActionWithMockTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_execute_returns_success() throws UnauthenticatedException {
        PostDao postDaoMock = Mockito.mock(PostDao.class);
        ArrayList<Post> posts = new ArrayList<>(List.of(
                createPost(1, "Mock Title 1", "Mock Content 1"),
                createPost(2, "Mock Title 2", "Mock Content 2")
        ));

        Mockito.when(postDaoMock.getAllWithPage(anyInt(), anyInt())).thenReturn(posts);

        IndexAction action = new IndexAction(postDaoMock);
        action.withSession(new HashMap(Map.of("user_id", 1)));

        String result = action.execute();

        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_returns_posts() throws UnauthenticatedException {
        // Prepare the mock
        PostDao postDaoMock = Mockito.mock(PostDao.class);
        ArrayList<Post> posts = new ArrayList<>(List.of(
                createPost(1, "Mock Title 1", "Mock Content 1"),
                createPost(2, "Mock Title 2", "Mock Content 2")
        ));
        Mockito.when(postDaoMock.getAllWithPage(anyInt(), anyInt())).thenReturn(posts);

        // Inject the mock into the action
        IndexAction action = new IndexAction(postDaoMock);
        // Create a session with authentication
        action.withSession(new HashMap<>(Map.of("user_id", 1)));

        // Execute the Action
        String result = action.execute();

        assertEquals("Mock Title 1", action.getPosts().get(0).getTitle());
    }

    private Post createPost(int id, String title, String content) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);

        return post;
    }
}
