package struts_blog.actions.guests;

import struts_blog.actions.BaseAction;
import struts_blog.daos.GuestDao;
import struts_blog.models.Guest;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class IndexAction extends BaseAction {
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
