package struts_blog.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoBase {
    String connectionUrl = "jdbc:mysql://localhost:3306/struts_blog?serverTimezone=UTC";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, "root", "password");
    }
}
