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

    public String execute() throws UnauthenticatedException {
        authenticate();

        User exisitingUser = userDao.find(user.getId());
        User updatedUser = updateUserFromForm(exisitingUser);

        if (userDao.update(updatedUser)) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    /*
     * How to update stuff with Struts
     *
     * When setting values on the User object with Struts, we can use OGNL.
     * When we use HTTP parameter keys like `user.email`, Struts sends `getUser()` to
     * the Action (and creates a new instance if this returns null), and then sends
     * `setEmail()` to that instance with the HTTP parameter value as the argument.
     *
     * This is nice, but often you need to be more restrictive. In many cases, you will only
     * be updating a limited set of fields and not every single one. There may also
     * be some fields that you want to protect and prevent the UI from directly updating.
     * With just OGNL and direct access to the User object, it's hard to control this.
     *
     * We also need to note that the HTTP parameters can be easily forged by malicious users.
     * Anybody can send any HTTP parameter key that they wish, not only the ones that we provide
     * in the HTTP form input fields. A malicious user could abuse OGNL and set a field that
     * we intended to hide. For example, a User may have a `adminRole` attribute that should only
     * be set by administrators. However, OGNL can set this value without restriction.
     *
     * In the current case, I only want to update the email if the password (and passwordConfirm)
     * field is blank. I need to control when the `password` field is updated.
     *
     * Here, I am separating the OGNL updated User object from the one that will actually be stored
     * in the database. I am using the OGNL update User object as a Form object that simply temporarily
     * stores the values from the HTTP request. This Form object will not be stored in the database.
     * Instead, we retrieve the existing User from the database and selectively update fields on this instance
     * based on the values in the Form object.
     *
     * For any Actions that save values to the database, I think this is how we should do it.
     * We should not let OGNL directly set values that may be saved to the database. Instead, we should
     * insist that each field is set deliberately. The instance that OGNL generates should merely be a temporary Form
     * object that stores the HTTP parameter values, and should never be directly saved to the database.
     *
     * 1. HTTP parameter values will be set into the Form object (generation of the Form object and setting values
     *    will be done by OGNL)
     * 2. The `execute()` action will load the previous instance from the database and selectively update it
     *    with values from the Form object. The fields to be updated should be purposefully selected with whitelisting.
     * 3. The updated previous instance should be saved to the database.
     * */
    private User updateUserFromForm(User exisitingUser) {
        exisitingUser.setEmail(user.getEmail());
        if (!user.getPassword().isBlank()) {
            exisitingUser.setPassword(user.getPassword());
        }

        return exisitingUser;
    }

    @Override
    public void validate() {
        User user = getUser();
        if (user.getPassword().isBlank() && user.getPasswordConfirm().isBlank()) {
            return;
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            addFieldError("user.password", "Password and Password Confirmation must match");
            addFieldError("user.passwordConfirm", "Password and Password Confirmation must match");
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

    @Override
    public String getTitle() {
        return "Update User";
    }
}
