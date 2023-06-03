package struts_blog.daos;

import struts_blog.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase<User> {


    public boolean update(User user) {
        try(Connection conn = getConnection()) {
            String sqlString = "UPDATE users SET email = ?, password_digest = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordDigest());
            ps.setLong(3, user.getId());

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
    public boolean create(User user) {
        try(Connection conn = getConnection()) {
            String sqlString = "INSERT INTO users (email, password_digest) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordDigest());

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected String getTable() {
        return "users";
    }

    protected User objectFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String passwordDigest = resultSet.getString("password_digest");
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPasswordDigest(passwordDigest);
        return user;
    }
}
