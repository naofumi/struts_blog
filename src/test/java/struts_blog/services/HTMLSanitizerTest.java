package struts_blog.services;

import junit.framework.TestCase;
import org.owasp.html.Sanitizers;

public class HTMLSanitizerTest extends TestCase {

    public void test_Sanitize() {
        System.out.println(HTMLSanitizer.sanitize("<b>Hello, World!</b>"));
    }
}
