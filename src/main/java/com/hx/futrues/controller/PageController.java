package com.hx.futrues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @GetMapping("/")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

    @GetMapping("order-list")
    public ModelAndView orderList(ModelAndView model) {
        model.setViewName("order-list");
        return model;
    }
}
