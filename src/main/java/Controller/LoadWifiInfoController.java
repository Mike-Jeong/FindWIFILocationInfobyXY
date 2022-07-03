package controller;

import config.ApplicationConfig;
import service.LoadWifiInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoadWifiInfoController implements IController {

    LoadWifiInfoService loadWifiInfoService;

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadWifiInfoService = ApplicationConfig.getLoadWifiInfoService();

        int count = loadWifiInfoService.insertWifiINfo();

        if (count == -1) {
            request.getRequestDispatcher("/WEB-INF/views/loadWifiFail.jsp").forward(request, response);
        } else {
            request.setAttribute("wifiCount", count);
            request.getRequestDispatcher("/WEB-INF/views/loadWifi.jsp").forward(request, response);
        }
    }
}