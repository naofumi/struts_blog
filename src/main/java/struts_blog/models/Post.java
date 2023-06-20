package struts_blog.models;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

public class Post implements Indexable {
    private int id;
    private String title;
    private String content;
    /*
     * Some projects escape user generated content before storage into the database.
     * This provides extra safety against XSS attacks so that even when the application
     * fails to perform XSS protection in the views, XSS cannot occur. This can be
     * done either inside the application (as I have shown below) or in a Servlet filter.
     * (This may be set in the `web.xml` configuration file)
     *
     * However, when applying escaping two or more times, some characters will become
     * garbled. This field allows us to test the behaviour of the application when
     * this occurs.
     *
     * Note that if you use the struts tags for displaying content, it will be escaped by
     * default. Therefore, if you use the tags as is to display content that is already
     * escaped, you will end up with garbled characters.
     *  */
    private String xssEscapedContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.xssEscapedContent = escapeHtml4(content);
    }

    public String getXssEscapedContent() {
        return xssEscapedContent;
    }

    public void setXssEscapedContent(String xssEscapedContent) {
        this.xssEscapedContent = xssEscapedContent;
    }

}
