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
    private final BigDecimal POUNDAGE_X = new BigDecimal(2);

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
    private String type;
    /**
     * 买卖方向
     */
    private Integer bbi;
    /**
     * 数量
     */
    private int number;
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
     * 手续费
     */
    private BigDecimal poundage;
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
     * @param platform   平台
     * @param type       品种
     * @param bbi        方向
     * @param number     数量
     * @param startTime  开仓时间
     * @param startPoint 开仓点位
     * @param poundage   手续费
     */
    public Orders(Integer platform, String type, Integer bbi, Integer number, String startTime, BigDecimal startPoint, BigDecimal poundage) {
        this.platform = platform;
        this.bbi = bbi;
        this.type = type;
        this.number = number;
        this.startTime = startTime;
        this.startPoint = startPoint;
        this.poundage = poundage.multiply(new BigDecimal(number));
    }

    /**
     * 平仓
     *
     * @param endPoint 平仓点位
     * @param endTime  平仓时间
     * @return
     */
    public boolean offsetTransaction(BigDecimal endPoint, String endTime, BigDecimal price) {
        this.endPoint = endPoint;
        this.endTime = endTime;
        this.status = 1;

        // 计算盈亏
        BigDecimal point;
        if (this.bbi == 1) {
            point = endPoint.subtract(this.startPoint);
        } else {
            point = this.startPoint.subtract(this.endPoint);
        }
        this.loss = point.multiply(price).multiply(new BigDecimal(number)).subtract(poundage);

        return true;
    }
}
