package struts_blog.daos;

import java.sql.*;
import java.util.ArrayList;
/*
* The base Data Access Object for the current application.
*
* This has the following design features.
*
* 1. It is designed for simple access to the database.
* 2. It also allows us to mock out the database access.
*
* Notes
*
* 1. With `createAndReturnSaved()`, the `id` of the object sent in as the argument
*    will be set from the `id` generated after saving in the database.
*    This is useful for auto-incremented rows, for example.
*    (see the implementations for examples)
* */
abstract class DaoBase<T> implements Refreshable {
    abstract protected String getTable();

    abstract protected T getObjectFromResultSet(ResultSet resultSet) throws SQLException;

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
                results.add(getObjectFromResultSet(rs));
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
                results.add(getObjectFromResultSet(rs));
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
                results.add(getObjectFromResultSet(rs));
            }
            return results;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* If not found, returns null */
    public T find(int id) {
        try (Connection conn = getConnection()) {
            String sqlString = "SELECT * FROM " + getTable() + " WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getObjectFromResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* If not found, returns null */
    public T findBy(String columnName, String value) {
        try (Connection conn = getConnection()) {
            String sqlString = "SELECT * FROM " + getTable() + " WHERE " + columnName + " = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, value);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getObjectFromResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (Connection conn = getConnection()) {
            String sqlString = "DELETE FROM " + getTable() + " WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setInt(1, id);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean truncate() {
        try (Connection conn = getConnection()) {
            String sqlString = "TRUNCATE TABLE " + getTable();
            PreparedStatement ps = conn.prepareStatement(sqlString);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract boolean update(T object);

    public abstract T createAndReturnSaved(T object);

    public boolean create(T object) {
        try(Connection conn = getConnection()) {
            PreparedStatement ps = getPreparedStatementForCreate(conn, object);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    abstract protected PreparedStatement getPreparedStatementForCreate(Connection conn, T object) throws SQLException;

    protected boolean isNotZero(int changedRows) {
        return changedRows != 0;
    }

    public void refreshTableData() {
        truncate();
    }
    protected int getIdOfLastInsert(Connection conn) throws SQLException {
        PreparedStatement lastInsertStatement = conn.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet lastInsertRs = lastInsertStatement.executeQuery();
        lastInsertRs.next();
        return lastInsertRs.getInt(1);
    }
}
