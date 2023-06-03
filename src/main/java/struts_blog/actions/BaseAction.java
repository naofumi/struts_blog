package struts_blog.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;

import java.util.Map;

public abstract class BaseAction extends ActionSupport implements SessionAware {
    public final static String VISITS_COUNT_SESSION_KEY = "vcsk";
    protected SessionMap<String, Object> sessionMap;

    public void withSession(Map<String, Object> session) {
        this.sessionMap = (SessionMap<String, Object>) session;
    }

    public int getVisitsCount() {
        Integer visitsCount = (Integer)sessionMap.get(VISITS_COUNT_SESSION_KEY);
        if (visitsCount == null) return 0;

        return visitsCount;
    }

    protected void incrementVisitsCount() {
        int visitsCount = getVisitsCount();

        sessionMap.put(VISITS_COUNT_SESSION_KEY, visitsCount + 1);
    }
}
