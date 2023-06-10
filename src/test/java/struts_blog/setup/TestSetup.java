package struts_blog.setup;

import struts_blog.daos.GuestDao;
import struts_blog.daos.MailDao;
import struts_blog.daos.PostDao;
import struts_blog.daos.UserDao;

public class TestSetup {
    UserDao userDao = new UserDao();
    PostDao postDao = new PostDao();
    MailDao mailDao = new MailDao();
    GuestDao guestDao = new GuestDao();

    public static void main(String[] args) {
        TestSetup instance = new TestSetup();
        instance.setUpDb();
    }

    public void setUpDb() {
        userDao.refreshTableData();
        postDao.refreshTableData();
        mailDao.refreshTableData();
        guestDao.refreshTableData();
    }
}
