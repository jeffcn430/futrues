package com.hx.futrues.service;

import com.hx.futrues.entity.Orders;
import com.hx.futrues.exception.FutrueException;

import java.math.BigDecimal;
import java.util.List;

public interface IOrdersService {
    /**
     * 获取仓位列表
     *
     * @return
     */
    List<Orders> getOrdersList();

    /**
     * 分页获取
     */
    boolean getOrders();

    /**
     * 开仓
     *
     * @return
     */
    boolean openingTransaction(Integer platform, Integer type, Integer bbi, Integer number, BigDecimal startPoint, String time) throws FutrueException;

    /**
     * 平仓
     *
     * @return
     */
    boolean offsetTransaction(Integer id, BigDecimal endPoint, String endTime, BigDecimal maxPoint, BigDecimal minPoint, String desc) throws FutrueException;

    /**
     * 创建一个已完成的单子
     *
     * @return
     */
    boolean createOrders(Orders order) throws FutrueException;


    /**
     * 获取统计后的订单信息
     *
     * @param type
     * @return
     */
    List<Orders> getOrdersCount(Integer type);
}
