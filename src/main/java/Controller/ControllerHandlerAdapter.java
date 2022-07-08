package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ControllerHandlerAdapter implements IHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof IController);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        IController controller = (IController) handler;
        HashMap<String, String> paramMap = createParamMap(request);

        return controller.process(paramMap);
    }

    private HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
