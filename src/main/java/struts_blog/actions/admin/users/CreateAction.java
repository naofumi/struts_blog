package struts_blog.actions.admin.users;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.OneTimeTokenService;
import struts_blog.models.User;
/*
 * This user uses a oneTimeToken for authentication via emails, etc.
 * Instead of putting this logic inside the Domain Model (here) as I
 * would normally do, I am experimenting with using a Service for this.
 *
 * The OneTimeTokenService is handling this.
 *
 * Some of the things that I have noticed
 *
 * 1. The Service very much violates the "tell-don't-ask" principle. It
 *    is manipulating the User object and doing all kinds of things with it.
 * 2. Because the Service has to do lots of stuff directly with the User object,
 *    it is forcing us to open up parts of the User object that I'm not totally
 *    comfortable with. For example, `setOneTimeToken()` is a setter that I
 *    really don't want to expose.
 * 3. One of my original hesitations about putting more logic into the Domain Model
 *    was due to the need for the User object to depend on the UserDao. It felt a
 *    bit strange at first, but on second thoughts, it's probably a good thing.
 *    A User object needing to persist itself is a natural thing, and because we
 *    are using a Dao, we aren't exposing the User to the SQL either. There is not
 *    problem with it.
 *
 * */
public class CreateAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;
    private User user;
//    private Mailer mailer;

    UserDao userDao = new UserDao();
    OneTimeTokenService oneTimeTokenService = new OneTimeTokenService();

    public CreateAction() {
    }

    public CreateAction(UserDao userDao) {
        this.userDao = userDao;
    }

    public String execute() throws UnauthenticatedException {
        authenticate();

        this.user = userDao.createAndReturnSaved(user);

        String oneTimePassword = oneTimeTokenService.generateOneTimeTokenForUser(user);
//        mailer.send(user.getEmail(), user.getOneTimeToken());

        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getTitle() {
        return "Create User";
    }
}
