package struts_blog.services;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class HTMLSanitizer {
    public static String sanitize(String html) {
        // https://www.javadoc.io/doc/com.googlecode.owasp-java-html-sanitizer/owasp-java-html-sanitizer/latest/org/owasp/html/Sanitizers.html
        // Change sanitizing policy as needed.
        PolicyFactory sanitizer = Sanitizers.FORMATTING.and(Sanitizers.BLOCKS);
        return sanitizer.sanitize(html);
    }
}
