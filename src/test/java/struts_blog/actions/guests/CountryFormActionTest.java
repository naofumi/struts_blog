/*
 * Testing without using the Struts framework
 *
 * In this case, we simply extend TestCase for unit testing of the Struts Actions.
 *
 * Struts Actions are designed to be tested this way, which is apparent from how it
 * injects all the HTTP request information through setters before calling `execute()`.
 * Therefore it seems appropriate that we do as much testing as we can as Unit tests.
 *
 * An Action's role is
 *
 * 1. Receive the HTTP parameters
 * 2. Send appropriate messages to the Model based on the these HTTP parameters.
 *    This may change the state of the App (update database tables) or send external requests.
 * 3. Set an appropriate return value and the values for the getters.
 *
 * Therefore, the test's role is to
 *
 * 1. This is related to validation so this should be tested there. Since this is done
 *    in the Interceptor, it should be tested using the StrutsTestCase
 * 2. To verify that the appropriate messages were send to the Models, we could use mocks.
 *    We do need to do DI for this though.
 *    Another approach is to just check the state of the Models. This may require access to
 *    the database during testing.
 * 3. Verify the return value from the Action. Also verify the values from the getters.
 *
 *
 * */

package struts_blog.actions.guests;

import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.junit.StrutsTestCase;
import struts_blog.models.GuestForm;
import struts_blog.setup.TestSetup;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

public class CountryFormActionTest extends StrutsTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        new TestSetup().setUpDb();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        new TestSetup().setUpDb();
    }

    public void test_execute_returns_success() throws ServletException, UnsupportedEncodingException {
        GuestForm sessionGuestForm = new GuestForm();
        sessionGuestForm.setNickname("New Nickname");
        request.getSession().setAttribute("guest", sessionGuestForm);

        String body = executeAction("/guests/countryForm.action");

        GuestForm guestForm = (GuestForm) findValueAfterExecute("guestForm");

        assertEquals("New Nickname", guestForm.getNickname());
        assertEquals("", guestForm.getCountry());
        assertEquals(200, response.getStatus());
        assertEquals("/WEB-INF/content/guests/countryForm.jsp", response.getForwardedUrl());
        // I haven't yet set it up to return JSP content
        assertEquals("", body);
    }

    public void test_execute_redirect_back_to_nickname_if_missing() throws Exception {
        ActionProxy actionProxy = getActionProxy("/guests/countryForm.action");
        CountryFormAction action = (CountryFormAction) actionProxy.getAction();
        String result = actionProxy.execute();

        assertEquals("backToNickname", result);
        assertEquals(302, response.getStatus());
    }


}
