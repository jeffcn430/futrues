package com.hx.futrues.entity;

import com.hx.futrues.utils.MoneyTools;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "platformId", referencedColumnName = "id")
    private Platform platform;
    /**
     * 品种
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "varietyId", referencedColumnName = "id")
    private Variety variety;
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
    private LocalDateTime startTime;
    /**
     * 平仓时间
     */
    private LocalDateTime endTime;
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
    private Integer status = 0;

    /**
     * 止盈点位
     */
    private BigDecimal limited = BigDecimal.ZERO;
    /**
     * 止盈盈亏
     */
    private BigDecimal limitedLoss = BigDecimal.ZERO;

    /**
     * 止损点位
     */
    private BigDecimal stop = BigDecimal.ZERO;
    /**
     * 止损盈亏
     */
    private BigDecimal stopLoss = BigDecimal.ZERO;

    /**
     * 带单老师
     */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    private Teacher teacher;

    /**
     * 备注
     */
    private String remarks;

    public Orders() {
    }

    /**
     * 开仓
     *
     * @param platform   平台
     * @param variety    品种
     * @param bbi        方向
     * @param number     数量
     * @param startTime  开仓时间
     * @param startPoint 开仓点位
     */
    public Orders(Platform platform, Variety variety, Integer bbi, Integer number, String startTime, BigDecimal startPoint) {
//        this.platform = platform;
//        this.bbi = bbi;
//        this.variety = variety;
//        this.number = number;
//        this.startTime = startTime;
//        this.startPoint = startPoint;
//        //计算手续费
//        this.poundage = MoneyTools.exchange(variety.getVarietyBase().getMoneyType(), variety.getPoundage().multiply(new BigDecimal(number)));
    }

    /**
     * 平仓
     *
     * @param endPoint 平仓点位
     * @param endTime  平仓时间
     * @param maxPoint 最高点位
     * @param minPoint 最低点位
     * @param desc     策略描述
     * @return
     */
    public boolean offsetTransaction(BigDecimal endPoint, String endTime, BigDecimal maxPoint, BigDecimal minPoint, String desc) {
//        this.endPoint = endPoint;
//        this.endTime = endTime;
//        this.status = 1;
//
//        // 计算盈亏
//        BigDecimal point;
//        if (this.bbi == 1) {
//            point = endPoint.subtract(this.startPoint);
//        } else {
//            point = this.startPoint.subtract(endPoint);
//        }
//
//        this.loss = countLoss(point);
        return true;
    }

    public void countLoss(Platform platform, Variety variety){
        // 设置状态
        this.status = 1;

        // 计算手续费
        this.poundage = MoneyTools.exchangeToUSD(variety.getVarietyBase().getMoneyType(), variety.getPoundage().multiply(new BigDecimal(number)));
        // 计算平仓盈亏
        this.loss = this.countLoss(variety.getVarietyBase(), this.startPoint, this.endPoint);

        // 计算止盈盈亏
        if(this.limited == null){
            this.limited = BigDecimal.ZERO;
        }
        if(this.limited != BigDecimal.ZERO){
            this.limitedLoss = this.countLoss(variety.getVarietyBase(), this.startPoint, this.limited);
        }
        // 计算止损盈亏
        if(this.stop == null){
            this.stop = BigDecimal.ZERO;
        }
        if(this.stop != BigDecimal.ZERO){
            this.stopLoss = this.countLoss(variety.getVarietyBase(), this.startPoint, this.stop);
        }
    }

    private BigDecimal countLoss(VarietyBase varietyBase, BigDecimal startPoint, BigDecimal endPoint) {
        // 计算盈亏
        BigDecimal point;
        if (this.bbi == 1) {
            point = endPoint.subtract(startPoint);
        } else {
            point = startPoint.subtract(endPoint);
        }

        point = point.divide(varietyBase.getMinPoint());
        BigDecimal loss = point.multiply(varietyBase.getPrice()).multiply(new BigDecimal(this.number));
        loss = MoneyTools.exchangeToUSD(varietyBase.getMoneyType(), loss);
        return loss;
    }
}


