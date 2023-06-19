package struts_blog.models;

import org.apache.commons.lang3.RandomStringUtils;
import struts_blog.daos.UserDao;

/*
 * I am using a Service to handle OneTimeTokens here. I am not sure that I like this
 * approach, and I think that putting this logic into the Domain Model looks nicer.
 *
 * My main objection is that Feature Envy is very severe. We are handing around User
 * objects all over the place. What is particularly jarring is that this kind of
 * makes it look ugly when you split up your methods into smaller ones.
 *
 * I think that the best way to solve this is to convert the OneTimeToken into
 * a Domain Model that contains a reference to the User. It doesn't have to be
 * a stateless Service.
 * */
public class OneTimeTokenService {
    private UserDao userDao = new UserDao();

    public OneTimeTokenService() {
    }

    public OneTimeTokenService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String generateOneTimeTokenForUser(User user) {
        int id = user.getId();
        String randomString = RandomStringUtils.randomAlphanumeric(10);
        String oneTimeToken = id + "T" + randomString;

        updateOneTimeTokenOnUser(oneTimeToken, user);

        return oneTimeToken;
    }

    public boolean confirmAndResetOneTimeTokenForUser(String token, User user) {
        if (token.equals(user.getOneTimeToken())) {
            resetUsersOneTimeToken(user);
            return true;
        } else {
            return false;
        }
    }

    /*
     * The ugly thing with Services is that you will create a lot of methods with
     * two or more arguments. Coming up with good names for these is hard.
     *
     * With an object-oriented approach using a Domain model, it is easy to identify
     * which argument is the Subject and which argument is the Object when calling the
     * method. However, with functions, the difference between each argument and how
     * they relate to each other is harder to describe.
     *
     * Here, I am trying to say that the OneTimeToken is an attribute of User and that
     * has to be updated. I am resorting to the use of `On` as part of the name,
     * but I'm not sure if this makes it obvious.
     * */
    private void updateOneTimeTokenOnUser(String oneTimeToken, User user) {
        user.setOneTimeToken(oneTimeToken);
        userDao.update(user);
    }

    private void resetUsersOneTimeToken(User user) {
        user.setOneTimeToken(null);
        userDao.update(user);
    }
}
