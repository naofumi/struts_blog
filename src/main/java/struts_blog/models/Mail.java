package struts_blog.models;

/*
 * Mail
 *
 * The purpose of this class is to provide a simple mailing system so that we can test how one-time tokens
 * work. Maybe I'll use a mock email service instead, because then we can learn more about connecting to an
 * external service.
 * */
public class Mail implements Indexable {
    private int id;
    private String title;
    private String sendTo;
    private String body;


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

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
