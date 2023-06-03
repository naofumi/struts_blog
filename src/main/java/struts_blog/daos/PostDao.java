package struts_blog.daos;

import struts_blog.models.Post;

import java.sql.*;

public class PostDao extends DaoBase<Post> {
    protected String getTable() {
        return "posts";
    }

    public boolean updatePost(Post post) {
        try(Connection conn = getConnection()) {
            String sqlString = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setLong(3, post.getId());

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * With MySQL, you can use `SELECT LAST_INSERT_ID();` to get the
     * ID of the row that was inserted. However, this is apparently not an
     * SQL standard, and I will not use that here.
     * Maybe later
     */
    public boolean createPost(Post post) {
        try(Connection conn = getConnection()) {
            String sqlString = "INSERT INTO posts (title, content) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    protected Post objectFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        return post;
    }
}
