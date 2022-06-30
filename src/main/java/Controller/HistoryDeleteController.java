package controller;

import dto.HistoryDto;
import service.HistoryService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "historyDelete", value = "/history/delete")
public class HistoryDeleteController extends HttpServlet {
    public void init() {

        System.out.println("history delete init");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        String date = request.getParameter("date");

        HistoryService historyService = new HistoryService();

        HistoryDto historyDto = new HistoryDto(id, lat, lnt, date);

        boolean result = historyService.deleteHistory(historyDto);

        if (result) {
            response.sendRedirect("/history");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('Error');</script>");
        }
    }
}