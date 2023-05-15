package struts_blog.models;

public class PostDao {

	public PostDao() {
		// TODO Auto-generated constructor stub
	}

	public Post[] getPosts() {
		Post post1 = new Post();
		post1.setTitle("First Title");
		post1.setContent("First Content");

		Post post2 = new Post();
		post2.setTitle("Second Title");
		post2.setContent("Second Content");
		
		Post[] posts ={post1, post2}; 
		return posts;
	}
}
