package Controller;

import DB.DBConnUtils;
import Model.Wifiinfo;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String x = request.getParameter("lat");
        String y = request.getParameter("lnt");

        if (x == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } else {

            ArrayList<Wifiinfo> wifiinfolist = new ArrayList<>();

            Connection conn = cm.getDBCP();

            try {
                stmt = conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {

                stmt.executeUpdate("insert into history (LAT, LNT, Date )values(" + x + "," + y + ", NOW())");

                rs = stmt.executeQuery("select round((ST_Distance_Sphere(POINT(" + " convert( " + y + ", decimal(15,5))," + "convert( " + x + ", decimal(15,5))), " +
                        "POINT(convert(w.LNT, decimal(15,5)), convert(w.LAT, decimal(15,7)))) * 0.001), 4)  as Distance, w.* " +
                        "from wifi w order by Distance limit 20");

                while (rs.next()) {

                    wifiinfolist.add(new Wifiinfo(
                                    rs.getString("Distance"),
                                    rs.getString("X_SWIFI_MGR_NO"),
                                    rs.getString("X_SWIFI_WRDOFC"),
                                    rs.getString("X_SWIFI_MAIN_NM"),
                                    rs.getString("X_SWIFI_ADRES1"),
                                    rs.getString("X_SWIFI_ADRES2"),
                                    rs.getString("X_SWIFI_INSTL_FLOOR"),
                                    rs.getString("X_SWIFI_INSTL_TY"),
                                    rs.getString("X_SWIFI_INSTL_MBY"),
                                    rs.getString("X_SWIFI_SVC_SE"),
                                    rs.getString("X_SWIFI_CMCWR"),
                                    rs.getString("X_SWIFI_CNSTC_YEAR"),
                                    rs.getString("X_SWIFI_INOUT_DOOR"),
                                    rs.getString("X_SWIFI_REMARS3"),
                                    rs.getString("LAT"),
                                    rs.getString("LNT"),
                                    rs.getString("WORK_DTTM")
                            )
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {

                cm.closeConnection(conn, stmt, rs, "main");

                try {
                    int result;
                    if (wifiinfolist.size() == 0) {
                        response.setContentType("text/html; charset=UTF-8");
                        PrintWriter writer = response.getWriter();
                        writer.println("<script>alert('와이파이 정보를 가져온 뒤, 다시 시도해 주세요'); location.href='index.jsp';</script>");
                        writer.close();
                    } else {
                        request.setAttribute("list", wifiinfolist);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                } catch (NumberFormatException e) {

                    response.setContentType("text/html; charset=UTF-8");
                    PrintWriter writer = response.getWriter();
                    writer.println("<script>alert('오류가 발생했습니다'); location.href='index.jsp';</script>");
                    writer.close();
                }
            }

        }
    }

    public void destroy() {
    }
}