package com.hx.futrues.controller;

import com.hx.futrues.entity.Platform;
import com.hx.futrues.service.IPlatformService;
import com.hx.futrues.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private IPlatformService platformService;

    @Autowired
    private ITeacherService teacherService;

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
        // 平台下拉列表
        List<Platform> platforms = platformService.platforms();
        model.addObject("platforms", platforms);

        // 期货品种
//        model.addObject("varietys", varietyService.findAllByPlatformId(platforms.get(0).getId()));

        // 显示页面
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

    /**
     * 增加完整单
     * @param model
     * @return
     */
    @GetMapping("order-finish")
    public ModelAndView orderFinish(ModelAndView model){
        model.setViewName("order-finish");
        model.addObject("teacher", teacherService.getTeacherList());
        return model;
    }
}
