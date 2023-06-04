package struts_blog.actions.admin.users;

import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.OneTimeTokenService;
import struts_blog.models.User;

public class CreateAction extends AdminBaseAction {
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
}
