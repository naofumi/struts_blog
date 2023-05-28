package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;
import junit.framework.TestCase;
import org.mockito.Mockito;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

public class CreateActionWithMockTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_execute_returns_success() {
        PostDao postDaoMock = Mockito.mock(PostDao.class);
        CreateAction action = new CreateAction(postDaoMock);

        Post post = createPost("Test title", "Test content");
        Mockito.when(postDaoMock.createPost(post)).thenReturn(true);

        action.setPost(post);
        String result = action.execute();

        assertEquals(ActionSupport.SUCCESS, result);
        verify(postDaoMock).createPost(post);
    }

    public void test_execute_creates_new_post() {
        PostDao postDaoMock = Mockito.mock(PostDao.class);
        CreateAction action = new CreateAction(postDaoMock);

        Post post = createPost("Test title", "Test content");
        Mockito.when(postDaoMock.createPost(post)).thenReturn(true);

        action.setPost(post);
        action.execute();

        verify(postDaoMock).createPost(post);
    }

    private Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return post;
    }
}
