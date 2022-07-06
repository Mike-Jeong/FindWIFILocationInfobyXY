package controller;

import java.util.Map;

public class MainPageController implements IController {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("main");
    }
}
