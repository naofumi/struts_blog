package struts_blog.models;

import org.apache.struts2.dispatcher.SessionMap;
import struts_blog.daos.UserDao;

import java.util.Map;

public class AuthenticationService {
    UserDao userDao = new UserDao();

    public User getUserFromSession(Map<String, Object> sessionMap) {
        Integer id = (Integer) sessionMap.get("user_id");
        if (id == null) {
            return null;
        }

        User user = userDao.find(id);
        if (user == null) {
            /*
             * If the user_id exists in the session but the User cannot be found in the database, then that means that
             * the session is broken. Therefore, invalidate it.
             * */
            if (sessionMap instanceof SessionMap) {
                ((SessionMap<String, Object>) sessionMap).invalidate();
            }
        }
        return user;
    }
}
