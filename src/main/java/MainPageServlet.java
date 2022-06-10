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

@WebServlet(name = "mainPage", value = "/main")
public class MainPageServlet extends HttpServlet {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void init() {

        System.out.println("Main init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        String x = request.getParameter("lat");
        String y = request.getParameter("lnt");
        System.out.println("?");

        if (x == null) {
            System.out.println("!!");
            request.getRequestDispatcher("/wifimain.jsp").forward(request, response);

        } else {

            ArrayList<String> s = new ArrayList<>();
            System.out.println("??");

            Connection conn = cm.getDBCP();

            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                rs = stmt.executeQuery("select * from wifi");

                while (rs.next()) {
                    String a1 = rs.getString("X_SWIFI_MGR_NO");
                    String a2 = rs.getString("X_SWIFI_WRDOFC");
                    String a3 = rs.getString("X_SWIFI_MAIN_NM");
                    String a4 = rs.getString("X_SWIFI_ADRES1");
                    String a5 = rs.getString("X_SWIFI_ADRES2");
                    String a6 = rs.getString("X_SWIFI_INSTL_FLOOR");
                    String a7 = rs.getString("X_SWIFI_INSTL_TY");
                    String a8 = rs.getString("X_SWIFI_INSTL_MBY");
                    String a9 = rs.getString("X_SWIFI_SVC_SE");
                    String a10 = rs.getString("X_SWIFI_CMCWR");
                    String a11 = rs.getString("X_SWIFI_CNSTC_YEAR");
                    String a12 = rs.getString("X_SWIFI_INOUT_DOOR");
                    String a13 = rs.getString("X_SWIFI_REMARS3");
                    String a14 = rs.getString("LAT");
                    String a15 = rs.getString("LNT");
                    String a16 = rs.getString("WORK_DTTM");


                   String combi = a1 + " " + a2 + " " + a3 + " " + a4 + " " + a5 + " " + a6
                           + " " + a7 + " " + a8 + " " + a9 + " " + a10 + " " + a11 + " " + a12
                           + " " + a13 + " " + a14 + " " + a15 + " " + a16;

                   s.add(combi);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                request.setAttribute("list", s);
                System.out.println("???");
                request.getRequestDispatcher("wifimain.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('오류');</script>");
            }
        }
    }

    public void destroy() {
    }
}