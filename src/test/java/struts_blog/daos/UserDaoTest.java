package struts_blog.daos;

import junit.framework.TestCase;
import struts_blog.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;

public class UserDaoTest extends TestCase {
    UserDao userDao = new UserDao();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testFindWithValidId() {
        User result = userDao.find(1);
        assertEquals("naofumi@mac.com", result.getEmail());
    }

    public void testFindByEmailWithExistingEmail() {
//        User result = userDao.findByEmail("naofumi@mac.com");
        User result = userDao.findBy("email", "naofumi@mac.com");
        assertEquals("naofumi@mac.com", result.getEmail());
    }

    public void testFindByEmailWithNonExistingEmail() {
        User result = userDao.findBy("email", "boo@mac.com");
        assertNull(result);
    }

}


//public class PaginationLinksTest extends TestCase {
//    public void setUp() throws Exception {
//        super.setUp();
//    }
//
//    public void testWhenPageIs1() {
//        PaginationLinks paginationLinks = new PaginationLinks("/posts", 1L, 5L);
//
//        assertEquals(null, paginationLinks.getPrevious());
//        assertEquals("/posts?page=2", paginationLinks.getNext());
//        Map<String, String> middleMap = Map.of("1", "/posts?page=1", "2", "/posts?page=2", "3", "/posts?page=3",
//                "4", "/posts?page=4", "5", "/posts?page=5");
//        assertEquals(middleMap, paginationLinks.getMiddle());
//    }
//
//    public void testWhenPageIsMiddle() {
//        PaginationLinks paginationLinks = new PaginationLinks("/posts", 3L, 5L);
//
//        assertEquals("/posts?page=2", paginationLinks.getPrevious());
//        assertEquals("/posts?page=4", paginationLinks.getNext());
//        Map<String, String> middleMap = Map.of("1", "/posts?page=1", "2", "/posts?page=2", "3", "/posts?page=3",
//                "4", "/posts?page=4", "5", "/posts?page=5");
//        assertEquals(middleMap, paginationLinks.getMiddle());
//    }
//}
