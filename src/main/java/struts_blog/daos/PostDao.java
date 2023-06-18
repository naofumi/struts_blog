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
        String sqlString = "INSERT INTO posts (title, content) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, post.getTitle());
        ps.setString(2, post.getContent());
        return ps;
    }

    @Override
    protected PreparedStatement getPreparedStatementForUpdate(Connection conn, Post post) throws SQLException {
        String sqlString = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, post.getTitle());
        ps.setString(2, post.getContent());
        ps.setLong(3, post.getId());

        return ps;
    }

    protected Post getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        return post;
    }

    @Override
    protected void seedFreshData() {
        Post post = new Post();
        post.setTitle("My first Blog Post");
        post.setContent("My first Blog Post Content with <b>Bold</b> stuff and some XSS <script>alert('hello')</script>");
        create(post);
    }
}
