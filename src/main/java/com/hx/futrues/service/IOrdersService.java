package com.hx.futrues.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IOrdersService {
    /**
     * 分页获取
     */
    boolean getOrders();

    /**
     * 开仓
     *
     * @return
     */
    boolean openingTransaction(Integer type, Integer bbi, BigDecimal startPoint, LocalDateTime startTime);

    /**
     * 平仓
     *
     * @return
     */
    boolean offsetTransaction(Integer id, BigDecimal endPoint, LocalDateTime endTime);
}
