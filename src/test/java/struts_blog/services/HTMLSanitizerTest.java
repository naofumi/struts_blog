package struts_blog.services;

import junit.framework.TestCase;

public class HTMLSanitizerTest extends TestCase {

    public void test_Sanitize() {
        System.out.println(HTMLSanitizer.sanitize("<b>Hello, World!</b>"));
    }
}
