package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.Post;
import struts_blog.setup.TestSetup;

public class PostDaoTest extends TestCase {
    PostDao postDao = new PostDao();

    public void setUp() throws Exception {
        super.setUp();
        new TestSetup().setUpDb();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        new TestSetup().setUpDb();
    }

    public void test_post_is_correctly_created_with_xss_escape_filled() {
        Post post = new Post();
        post.setTitle("New Title");
        post.setContent("New Content <b>bold</b>");
        post = postDao.createAndReturnSaved(post);

        Post reloadedPost = postDao.find(post.getId());
        assertEquals("New Content &lt;b&gt;bold&lt;/b&gt;", reloadedPost.getXssEscapedContent());
    }
}
