package controller;

import service.LoadWifiInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loadWifi", value = "/loadWifi")
public class LoadWifiInfoController extends HttpServlet {
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