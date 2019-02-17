package com.hx.futrues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

    /**
     * 仓位列表
     *
     * @param model
     * @return
     */
    @GetMapping("order-list")
    public ModelAndView orderList(ModelAndView model) {
        model.setViewName("order-list");
        return model;
    }

    /**
     * 建仓页面
     *
     * @param model
     * @return
     */
    @GetMapping("order-add")
    public ModelAndView orderAdd(ModelAndView model) {
        model.setViewName("order-add");
        return model;
    }

    /**
     * 平仓页面
     *
     * @param model
     * @return
     */
    @GetMapping("order-offset")
    public ModelAndView orderOffset(ModelAndView model) {
        model.setViewName("order-offset");
        return model;
    }
}
