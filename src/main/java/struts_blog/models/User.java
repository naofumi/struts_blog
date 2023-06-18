package struts_blog.models;

import org.apache.commons.codec.digest.DigestUtils;

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
public class User implements Indexable {
    private int id;
    private String email;
    /*
    * The password and passwordConfirm fields is only used when creating or updating the User.
    * It is necessary to run validations against the password.
    */
    private String password;
    private String passwordConfirm;
    private String passwordDigest;
    private String oneTimeToken;

    public boolean isMatchingPassword(String password) {
        return getHashedString(password).equals(passwordDigest);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOneTimeToken() {
        return oneTimeToken;
    }

    public void setOneTimeToken(String oneTimeToken) {
        this.oneTimeToken = oneTimeToken;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String password) {
        this.passwordConfirm = password;
    }
    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    private String getHashedString(String original) {
        return DigestUtils.sha512Hex(original);
    }
}
