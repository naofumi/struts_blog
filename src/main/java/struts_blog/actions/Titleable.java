package struts_blog.actions;

public interface Titleable {
    String getTitle();
    default String getWindowTitle() {
        return getTitle() + " | Struts Blog";
    }
}
