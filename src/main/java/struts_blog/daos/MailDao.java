package struts_blog.daos;

import struts_blog.models.Mail;
import struts_blog.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MailDao extends DaoBase<Mail> {
    protected String getTable() {
        return "mails";
    }

    public boolean update(Mail mail) {
        try(Connection conn = getConnection()) {
            String sqlString = "UPDATE mails SET title = ?, send_to = ?, body = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, mail.getTitle());
            ps.setString(2, mail.getSendTo());
            ps.setString(3, mail.getBody());
            ps.setInt(4, mail.getId());

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean create(Mail mail) {
        try(Connection conn = getConnection()) {
            String sqlString = "INSERT INTO mails (title, send_to, body) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, mail.getTitle());
            ps.setString(2, mail.getSendTo());
            ps.setString(3, mail.getBody());

            int changedRows = ps.executeUpdate();
            return isNotZero(changedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected PreparedStatement getPreparedStatementForCreate(Connection conn, Mail object) throws SQLException {
        return null;
    }
    protected PreparedStatement getPreparedStatementForUpdate(Connection conn, Mail object) throws SQLException {
        return null;
    }

    public Mail createAndReturnSaved(Mail mail) {
        try(Connection conn = getConnection()) {
            String sqlString = "INSERT INTO mails (title, send_to, body) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sqlString);
            ps.setString(1, mail.getTitle());
            ps.setString(2, mail.getSendTo());
            ps.setString(3, mail.getBody());

            ps.executeUpdate();

            mail.setId(getIdOfLastInsert(conn));

            return mail;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    protected Mail getObjectFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String sendTo = resultSet.getString("send_to");
        String body = resultSet.getString("body");
        Mail mail = new Mail();
        mail.setId(id);
        mail.setTitle(title);
        mail.setSendTo(sendTo);
        mail.setBody(body);
        return mail;
    }
}
