package struts_blog.models;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Country {
    static final private String COUNTRY_NAMES_TEXT = "/struts_blog/data/countries.txt";
    static private List countries;
    private String name;

    static public List<Country> getAllCountries() throws IOException {
        if (countries == null) {
            Country.countries = getAllCountriesFromTextFile();
        }
        return countries;
    }

    private static List<Country> getAllCountriesFromTextFile() throws IOException {
        ArrayList<Country> countries = new ArrayList<Country>();
        for (String name : allCountryNamesArray()) {
            if (name.isBlank()) {
                continue;
            }
            Country country = new Country();
            country.name = name;
            countries.add(country);
        }
        return countries;
    }

    private static InputStream inputStream() {
        return Country.class.getResourceAsStream(COUNTRY_NAMES_TEXT);
    }

    private static String allCountriesText() throws IOException {
        return IOUtils.toString(inputStream(), StandardCharsets.UTF_8);
    }

    private static String[] allCountryNamesArray() throws IOException {
        return allCountriesText().split("\n");
    }

    public String getName() {
        return name;
    }
}
