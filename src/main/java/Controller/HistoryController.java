package controller;

import config.ApplicationConfig;
import dto.HistoryDto;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class HistoryController implements IController {

    HistoryService historyService;

    @Override
    public ModelView process(Map<String, String> paramMap) {

        historyService = ApplicationConfig.getHistoryService();

        ArrayList<HistoryDto> historyList = historyService.getHistory();

        ModelView mv;
        if (historyList == null) {
            mv = new ModelView("error");
            mv.getModel().put("ErrorMessage", "오류가 발생했습니다");
        } else {
            mv = new ModelView("history");
            mv.getModel().put("historyList", historyList);
        }
        return mv;
    }
}