package service;

import db.DBConnUtils;
import domain.History;
import dto.HistoryDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HistoryService {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public int deleteHistory(String lat, String lnt, String date) {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }

        try {
            stmt.executeUpdate("delete from history where LAT = " + lat + " and LNT = " + lnt + "and Date = '" + date + "'");
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        } finally {
            cm.closeConnection(conn, stmt, rs, "history delete");
        }

        return 1;
    }

    public ArrayList<HistoryDto> getHistory() {

        ArrayList<HistoryDto> historyList = new ArrayList<>();

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
                historyList.add(new HistoryDto(history));
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
