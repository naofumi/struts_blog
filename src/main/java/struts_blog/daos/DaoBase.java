package struts_blog.daos;

import struts_blog.models.Post;
import struts_blog.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;

abstract class DaoBase<T> {
    abstract protected String getTable();

    abstract protected T objectFromResultSet(ResultSet resultSet) throws SQLException;

    String connectionUrl = "jdbc:mysql://localhost:3306/struts_blog?serverTimezone=UTC";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, "root", "password");
    }

    public ArrayList<T> getAll() {
        ArrayList<T> results = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sqlString = "SELECT * FROM " + getTable();
            PreparedStatement ps = conn.prepareStatement(sqlString);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(objectFromResultSet(rs));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCount() {
        try (Connection conn = getConnection()) {
            String sqlString = "SELECT COUNT(*) FROM " + getTable();
            PreparedStatement ps = conn.prepareStatement(sqlString);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<T> getAllWithLimit(int limit) {
        ArrayList<T> results = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sqlString = "SELECT * FROM " + getTable() + " LIMIT ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);

            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(objectFromResultSet(rs));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<T> getAllWithPage(int page, int perPage) {
        ArrayList<T> results = new ArrayList<>();
        int offset = (page - 1) * perPage;

        try (Connection conn = getConnection()) {
            String sqlString = "SELECT * FROM " + getTable() + " LIMIT ? OFFSET ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);

            ps.setLong(1, perPage);
            ps.setLong(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(objectFromResultSet(rs));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public T find(int id) {
        try (Connection conn = getConnection()) {
            String sqlString = "SELECT * FROM " + getTable() + " WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return objectFromResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T findBy(String columnName, String value) {
        try (Connection conn = getConnection()) {
            String sqlString = "SELECT * FROM " + getTable() + " WHERE " + columnName + " = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return objectFromResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (Connection conn = getConnection()) {
            String sqlString = "DELETE FROM posts WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setInt(1, id);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean isNotZero(int changedRows) {
        return changedRows != 0;
    }

}
