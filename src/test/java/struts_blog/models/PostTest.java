package struts_blog.models;

import junit.framework.TestCase;

public class PostTest extends TestCase {
    public void test_xss_escaped_content_is_autogenerated() {
        Post post = new Post();
        post.setContent("<b>This is bold</b>");

        assertEquals("&lt;b&gt;This is bold&lt;/b&gt;", post.getXssEscapedContent());
    }
}
