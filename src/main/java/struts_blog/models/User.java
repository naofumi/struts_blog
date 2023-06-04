package struts_blog.models;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
    private int id;
    private String email;
    private String passwordDigest;

    public boolean isMatchingPassword(String password) {
        // TODO: This should use a hashing algorithm
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

    public void setPassword(String password) {
        // TODO: This should use a hashing algorithm
        this.passwordDigest = getHashedString(password);
    }
    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    private String getHashedString(String original) {
        return DigestUtils.md5Hex(original);
    }
}
