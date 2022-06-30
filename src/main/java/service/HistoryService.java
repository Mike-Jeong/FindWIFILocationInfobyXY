package service;

import db.DBConnUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryService {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public int deleteHistory(String lat, String lnt, String date) {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }

        try {
            stmt.executeUpdate("delete from history where LAT = " + lat + "and LNT = " + lnt + "and Date = '" + date + "'");
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            cm.closeConnection(conn, stmt, rs, "history delete");
        }

        return 1;
    }
}
