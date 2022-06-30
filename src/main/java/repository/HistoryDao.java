package repository;

import db.DBConnUtils;
import domain.History;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HistoryDao {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public boolean insert(History history) {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        try {
            stmt.executeUpdate("insert into history (LAT, LNT, Date )values(" + history.getLAT() + "," + history.getLNT() + ", '" + history.getDate() + "' )");

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            cm.closeConnection(conn, stmt, rs, "wifiapi");
        }
        return true;
    }

    public boolean delete(History history) {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        try {
            stmt.executeUpdate("delete from history where LAT = " + history.getLAT() + " and LNT = " + history.getLNT() + " and Date = '" + history.getDate() + "'");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            cm.closeConnection(conn, stmt, rs, "history delete");
        }

        return true;
    }

    public ArrayList<History> get() {
        ArrayList<History> historyList = new ArrayList<>();

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        try {
            rs = stmt.executeQuery("select * from history order by Date");
            while (rs.next()) {
                History history = new History(rs.getString("LAT"), rs.getString("LNT"), rs.getString("Date"));
                historyList.add(history);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            cm.closeConnection(conn, stmt, rs, "history");
        }

        return historyList;
    }


}
