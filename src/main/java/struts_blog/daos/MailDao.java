package struts_blog.daos;

import struts_blog.models.Mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MailDao extends DaoBase<Mail> {
    protected String getTable() {
        return "mails";
    }

    @Override
    protected PreparedStatement getPreparedStatementForCreate(Connection conn, Mail mail) throws SQLException {
        String sqlString = "INSERT INTO mails (title, send_to, body) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, mail.getTitle());
        ps.setString(2, mail.getSendTo());
        ps.setString(3, mail.getBody());

        return ps;
    }

    protected PreparedStatement getPreparedStatementForUpdate(Connection conn, Mail mail) throws SQLException {
        String sqlString = "UPDATE mails SET title = ?, send_to = ?, body = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sqlString);
        ps.setString(1, mail.getTitle());
        ps.setString(2, mail.getSendTo());
        ps.setString(3, mail.getBody());
        ps.setInt(4, mail.getId());

        return ps;
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

    @Override
    protected void seedFreshData() {
        Mail mail = new Mail();
        mail.setTitle("Mail title");
        mail.setSendTo("naofumi@mac.com");
        mail.setBody("This is the Mail body");

        create(mail);
    }
}
