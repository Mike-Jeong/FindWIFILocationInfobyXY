package controller;

import config.ApplicationConfig;
import service.LoadWifiInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoadWifiInfoController implements IController {

    LoadWifiInfoService loadWifiInfoService;

    @Override
    public ModelView process(Map<String, String> paramMap) {

        loadWifiInfoService = ApplicationConfig.getLoadWifiInfoService();

        int count = loadWifiInfoService.insertWifiINfo();

        ModelView mv;
        if (count == -1) {
            mv = new ModelView("loadWifiFail");
        } else {
            mv = new ModelView("loadWifi");
            mv.getModel().put("wifiCount", count);
        }
        return mv;
    }
}