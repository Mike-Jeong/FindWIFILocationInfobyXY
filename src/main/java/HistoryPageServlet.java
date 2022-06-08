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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

            ArrayList<String> historylist = new ArrayList<>();

            Connection conn = cm.getDBCP();

            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                rs = stmt.executeQuery("select * from history");

                while (rs.next()) {
                    String a = rs.getString("ID");
                    String a1 = rs.getString("LAT");
                    String a2 = rs.getString("LNT");
                    String a3 = rs.getString("Date");


                   String combi = a + " " + a1 + " " + a2 + " " + a3;

                    historylist.add(combi);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                request.setAttribute("historylist", historylist);
                request.getRequestDispatcher("/history.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('오류');</script>");
            }

    }

    public void destroy() {
    }
}