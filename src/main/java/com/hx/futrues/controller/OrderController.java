package com.hx.futrues.controller;

import com.hx.futrues.entity.Orders;
import com.hx.futrues.exception.FutrueException;
import com.hx.futrues.service.IOrdersService;
import com.hx.futrues.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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
    public ResultData ordersList(Integer page, Integer limit) {
        Page<Orders> ordersPage = this.ordersService.getOrdersList(page, limit);
        return new ResultData(0, "成功", ordersPage.getContent(), (int) ordersPage.getTotalElements());
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
    public ResultData offsetTransaction(Integer orderId, BigDecimal endPoint, String endTime, BigDecimal maxPoint, BigDecimal minPoint, String desc) throws FutrueException {
        this.ordersService.offsetTransaction(orderId, endPoint, endTime, maxPoint, minPoint, desc);
        return new ResultData();
    }

    @RequestMapping(value = "orders/createOrders")
    public ResultData createOrders(Orders order) throws FutrueException {
        this.ordersService.createOrders(order);
        return new ResultData();
    }
}
