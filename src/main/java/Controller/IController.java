package controller;

import java.util.Map;

public interface IController {
    ModelView process(Map<String, String> paramMap);
}
