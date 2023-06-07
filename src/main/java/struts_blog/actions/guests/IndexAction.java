package struts_blog.actions.guests;

import struts_blog.daos.GuestDao;
import struts_blog.models.Guest;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class IndexAction {
    private GuestDao guestDao = new GuestDao();
    private ArrayList<Guest> guests;
    public String execute() {
        this.guests = guestDao.getAll();
        return SUCCESS;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }
}
