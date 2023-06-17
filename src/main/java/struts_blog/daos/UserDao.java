package struts_blog.daos;

import org.apache.commons.codec.digest.DigestUtils;
import struts_blog.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase<User> {

    protected PreparedStatement getPreparedStatementForUpdate(Connection conn, User user) throws SQLException {
        String sqlString = "UPDATE users SET email = ?, password_digest = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPasswordDigest());
        ps.setInt(3, user.getId());

        return ps;
    }

    protected PreparedStatement getPreparedStatementForCreate(Connection conn, User user) throws SQLException {
        String sqlString = "INSERT INTO users (email, password_digest) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPasswordDigest());

        return ps;
    }

    @Override
    public void refreshTableData() {
        super.refreshTableData();

        User user1 = new User();
        user1.setEmail("naofumi@mac.com");
        user1.setPasswordDigest(DigestUtils.sha512Hex("password"));
        create(user1);

        User user2 = new User();
        user2.setEmail("spongebob@example.com");
        user2.setPasswordDigest(DigestUtils.sha512Hex("password"));
        create(user2);
    }

    @Override
    protected String getTable() {
        return "users";
    }

    protected User getObjectFromResultSet(ResultSet resultSet) throws SQLException {
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
