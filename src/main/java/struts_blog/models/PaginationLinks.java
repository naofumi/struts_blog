package struts_blog.models;

import java.util.LinkedHashMap;

public class PaginationLinks {
    private final String url;
    private final int page;
    private final int maxPages;

    public PaginationLinks(String url, int page, int maxPages) {
        this.url = url;
        this.page = page;
        this.maxPages = maxPages;
    }

    public String getPrevious() {
        if (page > 1) {
            return url + "?page=" + (page - 1);
        } else {
            return null;
        }
    }

    public String getNext() {
        if (page < maxPages) {
            return url + "?page=" + (page + 1);
        } else {
            return null;
        }
    }

    public LinkedHashMap<String, String> getMiddle() {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        int current = 1;

        while (current <= maxPages) {
            result.put(String.valueOf(current), url + "?page=" + current);
            current++;
        }
        return result;
    }
}
