package struts_blog.setup;

import org.mockito.Mockito;
import struts_blog.daos.UserDao;
import struts_blog.models.AuthenticationService;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

public interface AuthenticationMockable {
    default AuthenticationService mockedAuthenticationServiceForEmail(String email) {
        UserDao userDao = new UserDao();
        AuthenticationService authServiceMock = Mockito.mock(AuthenticationService.class);
        Mockito.when(authServiceMock.userFromSession(any(Map.class)))
                .thenReturn(userDao.findBy("email", email));
        return authServiceMock;
    }

    default AuthenticationService mockedAuthenticationServiceUnauthenticated() {
        AuthenticationService authServiceMock = Mockito.mock(AuthenticationService.class);
        Mockito.when(authServiceMock.userFromSession(any(Map.class)))
                .thenReturn(null);
        return authServiceMock;
    }
}
