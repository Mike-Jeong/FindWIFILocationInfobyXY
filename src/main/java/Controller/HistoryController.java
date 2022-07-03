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

public class HistoryController implements IController {

    HistoryService historyService;

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        historyService = ApplicationConfig.getHistoryService();

        ArrayList<HistoryDto> historyList = historyService.getHistory();

        if (historyList == null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('Error');</script>");
        } else {
            request.setAttribute("historyList", historyList);
            request.getRequestDispatcher("/WEB-INF/views/history.jsp").forward(request, response);
        }
    }
}