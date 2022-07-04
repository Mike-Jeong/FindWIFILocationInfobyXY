package controller;

import config.ApplicationConfig;
import dto.HistoryDto;
import service.HistoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HistoryDeleteController implements IController {
    HistoryService historyService;

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*int id = Integer.parseInt(request.getParameter("id"));
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        String date = request.getParameter("date");

        historyService = ApplicationConfig.getHistoryService();

        HistoryDto historyDto = new HistoryDto(id, lat, lnt, date);

        boolean result = historyService.deleteHistory(historyDto);

        if (result) {
            response.sendRedirect("/history");
        }

        PrintWriter out = response.getWriter();
        out.println("<script>alert('Error'); location.href='/'; </script>");*/

        return null;
    }
}