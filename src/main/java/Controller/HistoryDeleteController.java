package controller;

import service.HistoryService;

import javax.servlet.ServletException;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        String date = request.getParameter("date");

        HistoryService historyService = new HistoryService();

        int result = historyService.deleteHistory(lat, lnt, date);

        if (result == 1) {
            response.sendRedirect("/history");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('Error');</script>");
        }
    }
}