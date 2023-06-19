package struts_blog.models;

import org.apache.commons.codec.digest.DigestUtils;

public class User implements Indexable {
    private int id;
    private String email;
    /*
    * The password and passwordConfirm fields are only used when creating or updating the User.
    * There are necessary to run validations against the password.
    * (The values are injected into the UserAction using the setters. During validation, the values are
    *  retrieved using the getters. That's why we need both the getters and setters here)
    */
    private String password;
    private String passwordConfirm;
    private String passwordDigest;
    private String oneTimeToken;

    /*
    * This checks whether the passwordDigest matches the password sent in as the argument.
    * */
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

    /*
    * From a UI perspective, we need to check whether the password field is blank or not
    * and adjusting behaviour accordingly. If the password field is blank, then we will leave
    * the password as is. This is an Action concern (Business logic) and not a Domain Model concern.
    * */
    public void setPassword(String password) {
        this.password = password;
        this.passwordDigest = getHashedString(password);
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String password) {
        this.passwordConfirm = password;
    }
    /*
    * Ideally, I would like to hide the getter and setter for passwordDigest since I
    * consider it internal, and it should not be necessary for how the authentication system
    * works. However, because we are using an external DAO for persistence, this needs to
    * be exposed. This is unlike the ActiveRecord pattern where the Entity should be responsible
    * for its own persistence. With the ActiveRecord pattern, we can encapsulate sensitive fields
    * inside the Entity and completely hide it from the outside. With the DAO pattern however, we need to expose it.
    * */
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
