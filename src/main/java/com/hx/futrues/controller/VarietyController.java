package com.hx.futrues.controller;

import com.hx.futrues.entity.Variety;
import com.hx.futrues.service.IVarietyService;
import com.hx.futrues.service.impl.VarietyServiceImpl;
import com.hx.futrues.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VarietyController {
    @Autowired
    private IVarietyService varietyService;

    @RequestMapping(value = "variety", method = RequestMethod.GET)
    public ResultData varietyList(Integer platformId) {
        List<Variety> list = varietyService.findAllByPlatformId(platformId);
        return new ResultData(0, "成功", list, list.size());
    }
}
