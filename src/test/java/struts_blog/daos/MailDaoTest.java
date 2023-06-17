package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.Mail;
import struts_blog.setup.TestSetup;

public class MailDaoTest extends TestCase {
    MailDao mailDao = new MailDao();

    public void setUp() throws Exception {
        super.setUp();
        new TestSetup().setUpDb();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        new TestSetup().setUpDb();
    }

    public void test_object_is_correctly_created() {
        Mail result = mailDao.find(1);

        assertEquals("Mail title", result.getTitle());
        assertEquals("naofumi@mac.com", result.getSendTo());
        assertEquals("This is the Mail body", result.getBody());
    }

    public void test_fields_are_correctly_assigned_on_create() {
        Mail mail = new Mail();
        mail.setTitle("New Title");
        mail.setSendTo("spongebob@example.com");
        mail.setBody("New Mail Body");

        Mail createdMail = mailDao.createAndReturnSaved(mail);
        Mail reloadedMail = mailDao.find(createdMail.getId());
        assertEquals("New Title", reloadedMail.getTitle());
        assertEquals("spongebob@example.com", reloadedMail.getSendTo());
        assertEquals("New Mail Body", reloadedMail.getBody());
    }

    public void test_fields_are_correctly_assigned_on_update() {
        Mail mail = mailDao.findBy("title", "Mail title");
        mail.setTitle("New Title");
        mail.setSendTo("spongebob@example.com");
        mail.setBody("New Mail Body");

        mailDao.update(mail);

        Mail reloadedMail = mailDao.find(mail.getId());
        assertEquals("New Title", reloadedMail.getTitle());
        assertEquals("spongebob@example.com", reloadedMail.getSendTo());
        assertEquals("New Mail Body", reloadedMail.getBody());
    }
}
