package struts_blog.models;

import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.daos.UserDao;

public class AuthenticationService {
    UserDao userDao = new UserDao();

    public User userFromSession(SessionMap<String, Object> sessionMap) {
        Integer id = (Integer) sessionMap.get("user_id");
        if (id == null)
            return null;

        User result = userDao.find(id);
        if (result == null) {
            /*
             * If the user_id exists in the session but the User cannot be found in the database, then that means that
             * the session is broken. Therefore, invalidate it.
             * */
            sessionMap.invalidate();
        }
        return result;
    }

}
