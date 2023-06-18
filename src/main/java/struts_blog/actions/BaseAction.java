package struts_blog.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseAction extends ActionSupport implements SessionAware {
    public final static String VISITS_COUNT_SESSION_KEY = "vcsk";
    public String flash;
    protected Map<String, Object> sessionMap = new HashMap<String, Object>();

    public void withSession(Map<String, Object> session) {
        this.sessionMap = session;
        prepareFlash();
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

    public void setFlash(String flash) {
        sessionMap.put("flash", flash);
    }

    public String getFlash() {
        return flash;
    }

    protected void invalidateSession() {
        if (sessionMap instanceof SessionMap) {
            ((SessionMap<String, Object>)sessionMap).invalidate();
        }
    }
    private void prepareFlash() {
        String value = (String)sessionMap.get("flash");
        if ((value != null) && (value.length() > 0)) {
            this.flash = value;
        }
        sessionMap.remove("flash");
    }
}
