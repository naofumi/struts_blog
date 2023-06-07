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

    public void test_find_with_valid_id() {
        Post result = postDao.find(1);

        assertEquals("My first Blog Post", result.getTitle());
    }
}
