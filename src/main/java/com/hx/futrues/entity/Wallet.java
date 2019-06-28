package com.hx.futrues.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 钱包
 */
@Data
@Entity
@Table
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 钱包id
     */
    private Integer id;
    /**
     * 当前持有现金
     */
    private BigDecimal cash;
    /**
     * 成本
     */
    private BigDecimal cost;
    /**
     * 盈利
     */
    private BigDecimal profit;
    /**
     * 理想的资金最大回撤
     */
    private Integer maxRetreat;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "platformId", referencedColumnName = "id")
    private Platform platform;

    public void changeCash(BigDecimal cash){
        this.cash = this.cash.add(cash);
    }
}
