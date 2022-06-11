package Controller;

import API.WifiApiService;
import DB.DBConnUtils;

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

        int count = WifiApiService.getWifiInfo();



        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        if (count == -1) {
            out.println("<html><body>");
            out.println("<style>\n" +
                    "    #wrapper{\n" +
                    "        width:80%;\n" +
                    "        margin:auto;\n" +
                    "    }\n" +
                    "    #txtContact{\n" +
                    "        width:fit-content;\n" +
                    "        margin:auto;\n" +
                    "    }\n" +
                    "    #home{\n" +
                    "        width:100px;\n" +
                    "        margin:auto;\n" +
                    "        display:block;\n" +
                    "    }\n" +
                    "</style>");
            out.println("<div id=\"wrapper\">");
            out.println("<h1 id=\"txtContact\"> WIFI 정보를 정상적으로 저장하지 못했습니다. <br>잠시 후 다시 시도해 주세요" + "</h1>");
            out.println("<a id=\"home\" href=\"./\">홈</a>");
            out.println("</div>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<style>\n" +
                    "    #wrapper{\n" +
                    "        width:80%;\n" +
                    "        margin:auto;\n" +
                    "    }\n" +
                    "    #txtContact{\n" +
                    "        width:fit-content;\n" +
                    "        margin:auto;\n" +
                    "    }\n" +
                    "    #home{\n" +
                    "        width:100px;\n" +
                    "        margin:auto;\n" +
                    "        display:block;\n" +
                    "    }\n" +
                    "</style>");
            out.println("<div id=\"wrapper\">");
            out.println("<h1 id=\"txtContact\">" + count + "개의 WIFI 정보를 정상적으로 저장하였습니다." + "</h1>");
            out.println("<a id=\"home\" href=\"./\">홈</a>");
            out.println("</div>");
            out.println("</body></html>");
        }
    }

    public void destroy() {
    }
}