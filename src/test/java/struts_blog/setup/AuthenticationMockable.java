package struts_blog.setup;

import org.mockito.Mockito;
import struts_blog.daos.UserDao;
import struts_blog.models.AuthenticationService;
import struts_blog.models.User;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
/*
* Usage
*
* action.setAuthenticationService(mockedAuthenticationServiceForEmail("test@example.com"));
*  */
public interface AuthenticationMockable {
    // Creates a mock with a currentUser with an email that is as specified
    default AuthenticationService mockedAuthenticationServiceForEmail(String email) {
        User user = new User();
        user.setEmail(email);

        AuthenticationService authServiceMock = Mockito.mock(AuthenticationService.class);
        Mockito.when(authServiceMock.userFromSession(any(Map.class)))
                .thenReturn(user);

        return authServiceMock;
    }

    // Creates a mock with no currentUser (unauthenticated)
    default AuthenticationService mockedAuthenticationServiceUnauthenticated() {
        AuthenticationService authServiceMock = Mockito.mock(AuthenticationService.class);
        Mockito.when(authServiceMock.userFromSession(any(Map.class)))
                .thenReturn(null);

        return authServiceMock;
    }
}
