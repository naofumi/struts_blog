package struts_blog.models;

import struts_blog.daos.UserDao;

public class Login {
    private String email;
    private String password;
    private UserDao userDao = new UserDao();

    public User getAuthenticatedUser() {
        User user = userDao.findBy("email", email);

        if (user != null && user.isMatchingPassword(password)) {
            return user;
        } else {
            return null;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
