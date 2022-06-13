package Controller;

import DB.DBConnUtils;
import Model.History;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "historydelete", value = "/history/delete")
public class HistoryDeleteServlet extends HttpServlet {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void init() {

        System.out.println("history delete init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        String date = request.getParameter("date");

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            stmt.executeUpdate("delete from history where LAT = " + lat + "and LNT = " + lnt + "and Date = '" + date + "'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cm.closeConnection(conn, stmt, rs, "history delete");
        }

        try {
            response.sendRedirect("/history");
        } catch (NumberFormatException e) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('삭제 오류');</script>");
        }

    }

    public void destroy() {
    }
}