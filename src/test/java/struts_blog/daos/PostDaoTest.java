package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.Post;
import struts_blog.models.User;
import struts_blog.setup.DbSetup;

public class PostDaoTest extends TestCase {
    PostDao postDao = new PostDao();

    public void setUp() throws Exception {
        super.setUp();
        new DbSetup().setUpDb();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        new DbSetup().setUpDb();
    }

    public void test_find_with_valid_id() {
        Post result = postDao.find(1);
        assertEquals("My first Blog Post", result.getTitle());
    }
}
