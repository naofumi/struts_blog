package struts_blog.actions.admin.users;

import struts_blog.actions.Titleable;
import struts_blog.actions.UnauthenticatedException;
import struts_blog.actions.admin.AdminBaseAction;
import struts_blog.daos.UserDao;
import struts_blog.models.User;

public class UpdateAction extends AdminBaseAction implements Titleable {
    private static final long serialVersionUID = 1L;
    UserDao userDao = new UserDao();
    private User user;
    private String passwordConfirm;

    public String execute() throws UnauthenticatedException {
        authenticate();

        User exisitingUser = userDao.find(user.getId());
        updateUserFromForm(exisitingUser);

        if (userDao.update(exisitingUser)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    private void updateUserFromForm(User exisitingUser) {
        exisitingUser.setEmail(user.getEmail());
        if (!user.getPassword().isBlank()) {
            exisitingUser.setPassword(user.getPassword());
        }
    }

    @Override
    public void validate() {
        User user = getUser();
        if (user.getPassword().isBlank() && getPasswordConfirm().isBlank()) {
            return;
        }
        if (!user.getPassword().equals(getPasswordConfirm())) {
            addFieldError("user.password", "Password and Password Confirmation must match");
            addFieldError("passwordConfirm", "Password and Password Confirmation must match");
        }
        if (user.getPassword().length() < 6) {
            addFieldError("user.password", "You password needs to be a minimum of 6 letters");
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String getTitle() {
        return "Update User";
    }
}
