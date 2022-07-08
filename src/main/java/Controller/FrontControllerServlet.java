package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServlet", urlPatterns = "/")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, IController> handlerMap = new HashMap<>();
    private final List<IHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServlet() {
        initHandlerMap();
        initHandlerAdapters();
    }

    private void initHandlerMap() {
        handlerMap.put("/", new MainPageController());
        handlerMap.put("/search", new SearchWifiInfoController());
        handlerMap.put("/loadWifi", new LoadWifiInfoController());
        handlerMap.put("/history", new HistoryController());
        handlerMap.put("/history/delete", new HistoryDeleteController());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerHandlerAdapter());
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        IHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);
        String viewName = mv.getViewName();

        if (viewName.equals("redirect")) {
            response.sendRedirect((String) mv.getModel().get("path"));
        } else {
            MyView myView = viewResolver(viewName);
            myView.render(mv.getModel(), request, response);
        }
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMap.get(requestURI);
    }

    private IHandlerAdapter getHandlerAdapter(Object handler) {
        for (IHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("HandlerAdapter 찾을 수 없습니다. handler = " + handler);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
