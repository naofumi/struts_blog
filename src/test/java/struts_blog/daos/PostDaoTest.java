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

    public void test_object_is_correctly_created() {
        Post result = postDao.find(1);

        assertEquals("My first Blog Post", result.getTitle());
        assertEquals("My first Blog Post Content", result.getContent());
    }

    public void test_fields_are_correctly_assigned_on_create() {
        Post post = new Post();
        post.setTitle("New Title");
        post.setContent("New Content");

        Post createdPost = postDao.createAndReturnSaved(post);
        Post reloadedPost = postDao.find(createdPost.getId());
        assertEquals("New Title", reloadedPost.getTitle());
        assertEquals("New Content", reloadedPost.getContent());
    }

    public void test_fields_are_correctly_assigned_on_update() {
        Post post = postDao.findBy("title", "My first Blog Post");
        post.setTitle("New Title");
        post.setContent("New Content");

        postDao.update(post);

        Post reloadedPost = postDao.find(post.getId());
        assertEquals("New Title", reloadedPost.getTitle());
        assertEquals("New Content", reloadedPost.getContent());
    }


}
