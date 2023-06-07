package struts_blog.daos;

import struts_blog.models.Post;

import java.sql.*;

public class PostDao extends DaoBase<Post> {
    protected String getTable() {
        return "posts";
    }

    public boolean update(Post post) {
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

    public boolean create(Post post) {
        try(Connection conn = getConnection()) {
            PreparedStatement ps = getPreparedStatementForCreate(conn, post);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected PreparedStatement getPreparedStatementForCreate(Connection conn, Post post) throws SQLException {
        String sqlString = "INSERT INTO posts (title, content) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, post.getTitle());
        ps.setString(2, post.getContent());
        return ps;
    }

    public Post createAndReturnSaved(Post post) {
        try(Connection conn = getConnection()) {
            String sqlString = "INSERT INTO posts (title, content) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());

            ps.executeUpdate();

            post.setId(getIdOfLastInsert(conn));

            return post;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected Post getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        return post;
    }

    @Override
    public void refreshTableData() {
        super.refreshTableData();

        Post post = new Post();
        post.setTitle("My first Blog Post");
        post.setContent("My first Blog Post Content");
        create(post);
    }
}
