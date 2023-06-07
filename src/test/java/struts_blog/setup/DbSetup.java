package struts_blog.setup;

import struts_blog.daos.MailDao;
import struts_blog.daos.PostDao;
import struts_blog.daos.UserDao;

public class DbSetup {
    UserDao userDao = new UserDao();
    PostDao postDao = new PostDao();
    MailDao mailDao = new MailDao();

    public static void main(String[] args) {
        DbSetup instance = new DbSetup();
        instance.setUpDb();
    }

    public void setUpDb() {
        userDao.refreshTableData();
        postDao.refreshTableData();
        mailDao.refreshTableData();
    }

}
