package struts_blog.models;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class CountryTest extends TestCase {
    public void test_allCountries() throws IOException {
        List<Country> allCountries = Country.getAllCountries();
        assertEquals("Afghanistan", allCountries.get(0).getName());
        assertEquals(196, allCountries.size());
    }
}
