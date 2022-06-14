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

@WebServlet(name = "historyPage", value = "/history")
public class HistoryPageServlet extends HttpServlet {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void init() {

        System.out.println("history init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<History> historylist = new ArrayList<>();

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            rs = stmt.executeQuery("select * from history order by Date");

            while (rs.next()) {
                historylist.add(new History(rs.getString("LAT"), rs.getString("LNT"), rs.getString("Date")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cm.closeConnection(conn, stmt, rs, "history");
        }

        try {
            request.setAttribute("historylist", historylist);
            request.getRequestDispatcher("/history.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('오류');</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
    }
}