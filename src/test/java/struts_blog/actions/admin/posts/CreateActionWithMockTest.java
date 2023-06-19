package struts_blog.actions.admin.posts;

import com.opensymphony.xwork2.ActionSupport;
import junit.framework.TestCase;
import org.mockito.Mockito;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.daos.PostDao;
import struts_blog.models.Authenticable;
import struts_blog.models.Post;
import struts_blog.setup.AuthenticationMockable;

import java.util.HashMap;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;

public class CreateActionWithMockTest extends TestCase implements AuthenticationMockable {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_fails_if_unauthenticated() {
        CreateAction action = new CreateAction();

        action.setAuthenticationService(mockedAuthenticationServiceUnauthenticated());

        Exception exception = assertThrows(UnauthenticatedException.class, () -> {
            action.execute();
        });
    }

    public void test_execute_returns_success() throws UnauthenticatedException {
        CreateAction action = new CreateAction();
        Post post = createPost("Test title", "Test content");

        PostDao postDaoMock = Mockito.mock(PostDao.class);
        Mockito.when(postDaoMock.create(post)).thenReturn(true);
        action.setPostDao(postDaoMock);

        action.setAuthenticationService(mockedAuthenticationServiceForEmail("test@example.com"));

        action.setPost(post);

        String result = action.execute();

        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_creates_new_post() throws UnauthenticatedException {
        CreateAction action = new CreateAction();
        Post post = createPost("Test title", "Test content");

        PostDao postDaoMock = Mockito.mock(PostDao.class);
        Mockito.when(postDaoMock.create(post)).thenReturn(true);

        action.setPostDao(postDaoMock);

        action.setAuthenticationService(mockedAuthenticationServiceForEmail("test@example.com"));

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
}
