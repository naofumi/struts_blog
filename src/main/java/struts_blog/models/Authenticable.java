package struts_blog.models;

public interface Authenticable {
    boolean isCorrectPassword(String password);
}
