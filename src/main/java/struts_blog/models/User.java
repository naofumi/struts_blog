package struts_blog.models;

import org.apache.commons.codec.digest.DigestUtils;

public class User implements Indexable, Authenticable {
    private int id;
    private String email;
    private String password;
    private String passwordDigest;
    private String oneTimeToken;

    /*
     * This is provided to illustrate the issue with setting models through OGNL
     * in the Action. Let`s assume that `isAdmin` should not be settable without
     * special permissions which are not generally available. However, we can see
     * that we cannot block an HTTP request with the parameters `user.admin` from
     * coming in through OGNL to set the value.
     *
     * To prevent this from getting into the database, we have to transfer the values
     * from the model that was modified from OGNL, to a separate model that we will
     * deliberately set, and we will save the second one to the database.
     * */
    private boolean isAdmin;

    public boolean isCorrectPassword(String password) {
        return getHashedString(password).equals(passwordDigest);
    }

    private String getHashedString(String original) {
        return DigestUtils.sha512Hex(original);
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
        this.passwordDigest = getHashedString(password);
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
