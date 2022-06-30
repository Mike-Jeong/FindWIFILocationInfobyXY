package controller;

import api.WifiApiService;
import db.DBConnUtils;
import service.LoadWifiInfoService;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loadWifi", value = "/loadWifi")
public class LoadWifiInfoController extends HttpServlet {
    private DBConnUtils cm;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void init() {

        System.out.println("LoadWifiController init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        LoadWifiInfoService loadWifiInfoService = new LoadWifiInfoService();
        int count = loadWifiInfoService.insertWifiINfo();

        if (count == -1) {
            request.getRequestDispatcher("/WEB-INF/views/loadWifiFail.jsp").forward(request, response);
        } else {
            request.setAttribute("wifiCount", count);
            request.getRequestDispatcher("/WEB-INF/views/loadWifi.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}