package struts_blog.actions.admin.posts;

import com.opensymphony.xwork2.ActionSupport;
import junit.framework.TestCase;
import org.mockito.Mockito;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

import java.util.HashMap;

import static org.mockito.Mockito.verify;

public class CreateActionWithMockTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_execute_returns_success() throws UnauthenticatedException {
        PostDao postDaoMock = Mockito.mock(PostDao.class);
        CreateAction action = new CreateAction(postDaoMock);

        action.withSession(getSessionWithLoggedInUserId(1));

        Post post = createPost("Test title", "Test content");
        Mockito.when(postDaoMock.create(post)).thenReturn(true);

        action.setPost(post);
        String result = action.execute();

        assertEquals(ActionSupport.SUCCESS, result);
        verify(postDaoMock).createAndReturnSaved(post);
    }

    public void test_execute_creates_new_post() throws UnauthenticatedException {
        PostDao postDaoMock = Mockito.mock(PostDao.class);
        CreateAction action = new CreateAction(postDaoMock);

        action.withSession(getSessionWithLoggedInUserId(1));

        Post post = createPost("Test title", "Test content");
        Mockito.when(postDaoMock.create(post)).thenReturn(true);

        action.setPost(post);
        action.execute();

        verify(postDaoMock).createAndReturnSaved(post);
    }

    private Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return post;
    }

    private HashMap<String, Object> getSessionWithLoggedInUserId(int id) {
        HashMap<String, Object> mockSession = new HashMap<>();
        mockSession.put("user_id", 1);
        return mockSession;
    }
}
