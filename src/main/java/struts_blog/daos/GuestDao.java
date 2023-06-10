package struts_blog.daos;

import struts_blog.models.Guest;

import java.sql.*;

public class GuestDao extends DaoBase<Guest> {
    protected String getTable() {
        return "guests";
    }

    public boolean update(Guest guest) {
        try(Connection conn = getConnection()) {
            String sqlString = "UPDATE guests SET nickname = ?, country = ?, twitter = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, guest.getNickname());
            ps.setString(2, guest.getCountry());
            ps.setString(3, guest.getTwitter());
            ps.setLong(4, guest.getId());

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean create(Guest guest) {
        try(Connection conn = getConnection()) {
            PreparedStatement ps = getPreparedStatementForCreate(conn, guest);

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected PreparedStatement preparedStatementForCreateAndReturnSaved(Connection conn, Guest object) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPreparedStatementForCreate(Connection conn, Guest guest) throws SQLException {
        String sqlString = "INSERT INTO guests (nickname, country, twitter) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, guest.getNickname());
        ps.setString(2, guest.getCountry());
        ps.setString(3, guest.getTwitter());
        return ps;
    }

    public Guest createAndReturnSaved(Guest guest) {
        try(Connection conn = getConnection()) {
            String sqlString = "INSERT INTO guests (nickname, country, twitter) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, guest.getNickname());
            ps.setString(2, guest.getCountry());
            ps.setString(2, guest.getTwitter());

            ps.executeUpdate();

            guest.setId(getIdOfLastInsert(conn));

            return guest;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected Guest getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nickname = resultSet.getString("nickname");
        String country = resultSet.getString("country");
        String twitter = resultSet.getString("twitter");
        Guest guest = new Guest();
        guest.setId(id);
        guest.setNickname(nickname);
        guest.setCountry(country);
        guest.setTwitter(twitter);
        return guest;
    }

    @Override
    public void refreshTableData() {
        super.refreshTableData();

        Guest guest1 = new Guest();
        guest1.setNickname("Nickname 1");
        guest1.setCountry("Country 1");
        guest1.setTwitter("Twitter 1");
        create(guest1);

        Guest guest2 = new Guest();
        guest2.setNickname("Nickname 2");
        guest2.setCountry("Country 2");
        guest2.setTwitter("Twitter 2");
        create(guest2);

    }
}
