import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loadWifi", value = "/loadwifi")
public class LoadWifiServlet extends HttpServlet {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void init() {

        System.out.println("loadwifi init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Connection conn = cm.getDBCP();

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String count = "";
        try {
            rs = stmt.executeQuery("select count(*) from wifi");

            while (rs.next()) {
                 count = rs.getString("count(*)");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + count + "개의 WIFI 정보를 정상적으로 저장하셨습니다." + "</h1>");
        out.println("<a href=\"./\">홈</a>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}