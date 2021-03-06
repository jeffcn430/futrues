package com.hx.futrues.controller;

import com.hx.futrues.entity.Platform;
import com.hx.futrues.service.IPlatformService;
import com.hx.futrues.service.ITeacherService;
import com.hx.futrues.service.IVarietyService;
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

    @Autowired
    private IVarietyService varietyService;


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
        model.addObject("platforms", platformService.platforms());
        model.addObject("teachers", teacherService.getTeacherList());
        model.addObject("varietys", varietyService.findAll());
        return model;
    }

    @GetMapping("echarts11")
    public ModelAndView echarts11(ModelAndView model){
        model.setViewName("echarts11");
        return model;
    }

    @GetMapping("echarts1")
    public ModelAndView echarts1(ModelAndView model){
        model.setViewName("echarts1");
        return model;
    }

    @GetMapping("echarts2")
    public ModelAndView echarts2(ModelAndView model){
        model.setViewName("echarts2");
        return model;
    }

    @GetMapping("echarts3")
    public ModelAndView echarts3(ModelAndView model){
        model.setViewName("echarts3");
        return model;
    }

    @GetMapping("echarts4")
    public ModelAndView echarts4(ModelAndView model){
        model.setViewName("echarts4");
        return model;
    }

    @GetMapping("echarts5")
    public ModelAndView echarts5(ModelAndView model){
        model.setViewName("echarts5");
        return model;
    }

    @GetMapping("echarts6")
    public ModelAndView echarts6(ModelAndView model){
        model.setViewName("echarts6");
        return model;
    }

    @GetMapping("echarts7")
    public ModelAndView echarts7(ModelAndView model){
        model.setViewName("echarts7");
        return model;
    }

    @GetMapping("echarts8")
    public ModelAndView echarts8(ModelAndView model){
        model.setViewName("echarts8");
        return model;
    }
}
