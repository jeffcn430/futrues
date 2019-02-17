package com.hx.futrues.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细
 */
@Data
@Entity
@Table
public class Orders implements Serializable {
    /**
     * 订单编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 交易平台
     */
    private int platform;
    /**
     * 品种
     */
    private Integer type;
    /**
     * 买卖方向
     */
    private Integer bbi;
    /**
     * 建仓时间
     */
    private String startTime;
    /**
     * 平仓时间
     */
    private String endTime;
    /**
     * 开仓点位
     */
    private BigDecimal startPoint;
    /**
     * 平仓点位
     */
    private BigDecimal endPoint;
    /**
     * 盈亏
     */
    private BigDecimal loss;

    /**
     * 平仓状态
     */
    private int status;

    public Orders() {
    }

    /**
     * 开仓
     *
     * @param type       品种
     * @param startTime  开仓时间
     * @param startPoint 开仓点位
     */
    public Orders(Integer bbi, Integer type, String startTime, BigDecimal startPoint) {
        this.platform = platform;
        this.type = type;
        this.startTime = startTime;
        this.startPoint = startPoint;
    }

    /**
     * 平仓
     *
     * @return
     */
    public boolean offsetTransaction(BigDecimal endPoint, String endTime) {
        this.endPoint = endPoint;
        this.endTime = endTime;
        this.status = 1;
        return true;
    }
}
