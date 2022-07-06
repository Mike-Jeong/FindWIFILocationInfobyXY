package controller;

import config.ApplicationConfig;
import dto.HistoryDto;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class HistoryDeleteController implements IController {
    HistoryService historyService;

    @Override
    public ModelView process(Map<String, String> paramMap) {
        int id = Integer.parseInt(paramMap.get("id"));
        String lat = paramMap.get("lat");
        String lnt = paramMap.get("lnt");
        String date = paramMap.get("date");

        historyService = ApplicationConfig.getHistoryService();

        HistoryDto historyDto = new HistoryDto(id, lat, lnt, date);

        boolean result = historyService.deleteHistory(historyDto);

        ModelView mv;
        if (!result) {
            mv = new ModelView("error");
            mv.getModel().put("ErrorMessage", "오류가 발생했습니다");
        } else {
            mv = new ModelView("redirect");
            mv.getModel().put("path", "/history");
        }
        return mv;
    }
}