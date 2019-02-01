package com.hx.futrues.service;

import com.hx.futrues.entity.Orders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    boolean openingTransaction(Integer type, Integer bbi, BigDecimal startPoint, String startTime);

    /**
     * 平仓
     *
     * @return
     */
    boolean offsetTransaction(Integer id, BigDecimal endPoint, String endTime);
}
