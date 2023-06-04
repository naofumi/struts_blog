package struts_blog.actions;

public class UnauthenticatedException extends Exception {
    public UnauthenticatedException(String errorMessage) {
        super(errorMessage);
    }
}
