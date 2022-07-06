package controller;

import config.ApplicationConfig;
import dto.WifiInfoDto;
import service.SearchWifiInfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class SearchWifiInfoController implements IController {

    SearchWifiInfoService searchWifiInfoService;

    @Override
    public ModelView process(Map<String, String> paramMap) {
        double lat = Double.parseDouble(paramMap.get("lat"));
        double lnt = Double.parseDouble(paramMap.get("lnt"));

        searchWifiInfoService = ApplicationConfig.getSearchWifiInfoService();

        ArrayList<WifiInfoDto> wifiInfoList = searchWifiInfoService.getWifiInfoList(lat, lnt);

        ModelView mv;
        if (wifiInfoList == null) {
            mv = new ModelView("error");
            mv.getModel().put("ErrorMessage", "오류가 발생했습니다");
        } else if (wifiInfoList.size() == 0) {
            mv = new ModelView("error");
            mv.getModel().put("ErrorMessage", "와이파이 정보를 가져온 뒤, 다시 시도해 주세요");
        } else {
            mv = new ModelView("search");
            mv.getModel().put("list", wifiInfoList);
        }
        return mv;
    }
}