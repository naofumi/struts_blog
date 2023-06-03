package struts_blog.actions.posts;

import com.opensymphony.xwork2.ActionSupport;
import junit.framework.TestCase;
import struts_blog.actions.admin.posts.CreateAction;
import struts_blog.daos.PostDao;
import struts_blog.models.Post;

import java.util.ArrayList;

public class CreateActionTest extends TestCase {
    private CreateAction action;
    private PostDao postDao;

    public void setUp() throws Exception {
        super.setUp();
        this.action = new CreateAction();
        this.postDao = new PostDao();
    }

    public void test_execute_returns_success() {
        Post post = createPost("Test title", "Test content");
        action.setPost(post);

        String result = action.execute();

        assertEquals(ActionSupport.SUCCESS, result);
    }

    public void test_execute_saves_post_into_database() {
        Post post = createPost("Test title", "Test content");
        action.setPost(post);

        String result = action.execute();

        assertEquals("Test title", lastPostInDB().getTitle());
    }

    private Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return post;
    }

    private Post lastPostInDB() {
        ArrayList<Post> posts = postDao.getAll();
        return posts.get(posts.size() - 1);
    }
}
