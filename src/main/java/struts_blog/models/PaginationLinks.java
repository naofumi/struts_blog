package struts_blog.models;

import java.util.LinkedHashMap;

public class PaginationLinks {
    private final String baseUrl;
    private final int page;
    private final int count;
    private final int perPage;

    public PaginationLinks(String baseUrl, int page, int count, int perPage) {
        this.baseUrl = baseUrl;
        this.page = page;
        this.count = count;
        this.perPage = perPage;
    }

    public String getPrevious() {
        if (page > 1) {
            return baseUrl + "?page=" + (page - 1);
        } else {
            return null;
        }
    }

    public String getNext() {
        if (page < getMaxPages()) {
            return baseUrl + "?page=" + (page + 1);
        } else {
            return null;
        }
    }

    public LinkedHashMap<String, String> getMiddle() {
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        int current = 1;

        while (current <= getMaxPages()) {
            result.put(String.valueOf(current), baseUrl + "?page=" + current);
            current++;
        }
        return result;
    }

    public int getMaxPages() {
        return (count / perPage) + 1;
    }
}
