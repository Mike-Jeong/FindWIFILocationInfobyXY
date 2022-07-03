package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServlet", urlPatterns = "/")
public class FrontControllerServlet extends HttpServlet {

    private Map<String, IController> controllerMap = new HashMap<>();

    public FrontControllerServlet() {
        controllerMap.put("/", new MainPageController());
        controllerMap.put("/search", new SearchWifiInfoController());
        controllerMap.put("/loadWifi", new LoadWifiInfoController());
        controllerMap.put("/history", new HistoryController());
        controllerMap.put("/history/delete", new HistoryDeleteController());
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        IController controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);

    }
}
