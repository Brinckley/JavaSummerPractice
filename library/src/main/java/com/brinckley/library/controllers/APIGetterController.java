package com.brinckley.library.controllers;

import com.brinckley.library.api.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIGetterController {

    private final APIService apiService;

    @Autowired
    public APIGetterController (APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api-worker")
    public String api(Model model) {
        String res = apiService.APIGetter();
        model.addAttribute("res", res);
        return "/api-worker";
    }

}
