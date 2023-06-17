package struts_blog.daos;

import struts_blog.models.Indexable;
import struts_blog.models.Mail;
import struts_blog.models.Post;
import struts_blog.models.User;

import java.sql.*;
import java.util.ArrayList;

/*
 * The base Data Access Object.
 *
 * This has the following design features.
 *
 * 1. It is designed to work with only one database table that
 *    is directly mapped to the Entity.
 *    For example, if you have a Post entity with the properties
 *    `title` and `content` and you have the setters and getters for these,
 *    `DaoBase` will allow you to easily create a PostDao to do CRUD operations
 *    on the `posts` table with the fields `title` and `content`.
 * 2. For general use, you are required to subclass `DaoBase` and create a `PostDaoBase`
 *    for example. You are then required to override `getTable()`, `getObjectFromResultSet()`,
 *    `getPreparedStatementForCreate()` and `getPreparedStatementForUpdate()`. This
 *    will allow you to use all the CRUD methods provided.
 * 3. `DaoBase` also lets you refresh the database which is good for seeding during
 *     development or testing. Call `refreshTableData()` to truncate the table
 *     and to seed new data. To seed data, you need to override `seedFreshData()` and
 *     provide the code to seed the database.
 *
 * Notes
 *
 * 1. With `createAndReturnSaved()`, the `id` of the object sent in as the argument
 *    will be set from the `id` generated after saving in the database.
 *    This is useful for auto-incremented rows, for example.
 *    (see the implementations for examples)
 * */
abstract class DaoBase<T extends Indexable> implements Refreshable {
    abstract protected String getTable();

    abstract protected T getObjectFromResultSet(ResultSet resultSet) throws SQLException;

    abstract protected PreparedStatement getPreparedStatementForCreate(Connection conn, T object) throws SQLException;

    abstract protected PreparedStatement getPreparedStatementForUpdate(Connection conn, T object) throws SQLException;

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

    public boolean create(T object) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = getPreparedStatementForCreate(conn, object);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T createAndReturnSaved(T object) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = getPreparedStatementForCreate(conn, object);
            ps.executeUpdate();

            object.setId(getIdOfLastInsert(conn));

            return object;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(T object) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = getPreparedStatementForUpdate(conn, object);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void refreshTableData() {
        truncate();
        seedFreshData();
    }

    protected void seedFreshData() {
        // Override with code to seed data
    }

    protected boolean isNotZero(int changedRows) {
        return changedRows != 0;
    }

    protected int getIdOfLastInsert(Connection conn) throws SQLException {
        PreparedStatement lastInsertStatement = conn.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet lastInsertRs = lastInsertStatement.executeQuery();
        lastInsertRs.next();
        return lastInsertRs.getInt(1);
    }
}
