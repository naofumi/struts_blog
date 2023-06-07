package struts_blog.models;

import junit.framework.TestCase;

import java.util.Map;

public class PaginationLinksTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testWhenPageIs1() {
        PaginationLinks paginationLinks = new PaginationLinks("/posts", 1, 10,5);

        assertNull(paginationLinks.getPrevious());
        assertEquals("/posts?page=2", paginationLinks.getNext());

        Map<String, String> middleMap = Map.of("1", "/posts?page=1", "2", "/posts?page=2", "3", "/posts?page=3",
                                                "4", "/posts?page=4", "5", "/posts?page=5");
        assertEquals(middleMap, paginationLinks.getMiddle());
    }

    public void testWhenPageIs100() {
        PaginationLinks paginationLinks = new PaginationLinks("/posts", 100, 10,5);

        assertEquals(null, paginationLinks.getPrevious());
        assertEquals(null, paginationLinks.getNext());
        Map<String, String> middleMap = Map.of();
        assertEquals(middleMap, paginationLinks.getMiddle());
    }

    public void testWhenPageIsMiddle() {
        PaginationLinks paginationLinks = new PaginationLinks("/posts", 3, 10, 5);

        assertEquals("/posts?page=2", paginationLinks.getPrevious());
        assertEquals("/posts?page=4", paginationLinks.getNext());
        Map<String, String> middleMap = Map.of("1", "/posts?page=1", "2", "/posts?page=2", "3", "/posts?page=3",
                "4", "/posts?page=4", "5", "/posts?page=5");
        assertEquals(middleMap, paginationLinks.getMiddle());
    }
}
