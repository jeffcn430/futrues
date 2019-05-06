package com.hx.futrues.controller;

import com.hx.futrues.entity.Orders;
import com.hx.futrues.exception.FutrueException;
import com.hx.futrues.service.IOrdersService;
import com.hx.futrues.utils.ResultData;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    /**
     * 获取仓位列表
     *
     * @return
     */
    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public ResultData ordersList() {
        List<Orders> ordersList = this.ordersService.getOrdersList();
        return new ResultData(0, "成功", ordersList, ordersList.size());
    }

    /**
     * 建仓
     *
     * @param platform
     * @param type
     * @param bbi
     * @param number
     * @param startPoint
     * @param startTime
     * @return
     */
    @RequestMapping(value = "orders/openingTransaction")
    public ResultData openingTransaction(Integer platform, Integer type, Integer bbi, Integer number, BigDecimal startPoint, String startTime) throws FutrueException {
        this.ordersService.openingTransaction(platform, type, bbi, number, startPoint, startTime);
        return new ResultData();
    }

    /**
     * 平仓
     *
     * @param orderId
     * @param endPoint
     * @param endTime
     * @return
     */
    @RequestMapping(value = "orders/offsetTransaction")
    public ResultData offsetTransaction(Integer orderId, BigDecimal endPoint, String endTime, BigDecimal maxPoint, BigDecimal minPoint, String desc)  throws FutrueException{
        this.ordersService.offsetTransaction(orderId, endPoint, endTime, maxPoint, minPoint, desc);
        return new ResultData();
    }
}
