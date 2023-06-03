package struts_blog.models;

/*
* This interface is used to create paginatable views from Actions.
*
* Because the pagination is bound to the Action, only one paginatable item per page is possible.
* (you cannot have more than one paginatable table in a single view with the interface alone)
*
* */
public interface Paginatable {
    /*
    * Instead fo requiring the Action to implement this Interface, I think it would be
    * better for the Action to directly implement the `getPaginationLinks()` method and
    * creating an instance of PaginationLinks with the necessary parameters.
    *
    * This would allow for multiple paginations per Action and would decouple Pagination
    * from Actions in general.
    * */
    default public PaginationLinks getPaginationLinks() {
        return new PaginationLinks(getBaseUrl(), getPage(), getMaxPages());
    }

    default public int getMaxPages() {
        return (getCount() / getPerPage()) + 1;
    }

    public String getBaseUrl();
    public int getPage();

    public int getPerPage();

    public int getCount();
}
