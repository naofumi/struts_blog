package struts_blog.daos;

import struts_blog.models.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostDao extends DaoBase<Post> {
    protected String getTable() {
        return "posts";
    }

    @Override
    protected PreparedStatement getPreparedStatementForCreate(Connection conn, Post post) throws SQLException {
        String sqlString = "INSERT INTO posts (title, content, xss_escaped_content) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, post.getTitle());
        ps.setString(2, post.getContent());
        ps.setString(3, post.getXssEscapedContent());
        return ps;
    }

    @Override
    protected PreparedStatement getPreparedStatementForUpdate(Connection conn, Post post) throws SQLException {
        String sqlString = "UPDATE posts SET title = ?, content = ?, xss_escaped_content = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, post.getTitle());
        ps.setString(2, post.getContent());
        ps.setString(3, post.getXssEscapedContent());
        ps.setLong(4, post.getId());

        return ps;
    }

    protected Post getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        String xssEscapedContent = resultSet.getString("xss_escaped_content");
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setXssEscapedContent(xssEscapedContent);
        return post;
    }

    public boolean createImplementedUsingLambda(Post post) {
        return mutateWithPreparedStatementFunction((conn) -> {
            String sqlString = "INSERT INTO posts (title, content, xss_escaped_content) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getXssEscapedContent());
            return ps;
        });
    }

    @Override
    protected void seedFreshData() {
        Post post = new Post();
        post.setTitle("My first Blog Post");
        post.setContent("My first Blog Post Content with <b>Bold</b> stuff and some XSS <script>alert('XSS injection alert')</script>");
        create(post);
    }
}
