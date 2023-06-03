package struts_blog.models;

import struts_blog.daos.UserDao;

public class Login {
    private String email;
    private String password;
    private UserDao userDao = new UserDao();

    public boolean isValid() {
        User user = userDao.findBy("email", email);
        if (user == null) { return false; }

        return user.isMatchingPassword(password);
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
