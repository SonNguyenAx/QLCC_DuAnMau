package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * createAt Dec 23, 2020
 *
 * @author Đỗ Tuấn Anh <daclip26@gmail.com>
 */
public class Session {

    private int id, idUser;
    private Timestamp startTime, endTime;
    private User user;
    private String message;

    public Session() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public User getUser() {
        return user;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            this.idUser = user.getId();
        }
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Session getFromResultSet(ResultSet rs) throws SQLException {
        Session s = new Session();
        s.setId(rs.getInt("id"));
        s.setIdUser(rs.getInt("idUser"));
        s.setMessage(rs.getNString("message"));
        s.setStartTime(rs.getTimestamp("startTime"));
        s.setEndTime(rs.getTimestamp("endTime"));
        return s;
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", idUser=" + idUser + ", startTime=" + startTime + ", endTime=" + endTime + ", message=" + message + '}';
    }

}
