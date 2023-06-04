package struts_blog.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;

import java.util.Map;

public abstract class BaseAction extends ActionSupport implements SessionAware {
    public final static String VISITS_COUNT_SESSION_KEY = "vcsk";
    public String flash;
    protected SessionMap<String, Object> sessionMap;

    public void withSession(Map<String, Object> session) {
        this.sessionMap = (SessionMap<String, Object>) session;
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

    private void prepareFlash() {
        String value = (String)sessionMap.get("flash");
        if ((value != null) && (value.length() > 0)) {
            this.flash = value;
        }
        sessionMap.remove("flash");
    }
}
