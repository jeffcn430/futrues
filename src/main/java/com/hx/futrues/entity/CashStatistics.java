package com.hx.futrues.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 资金每日统计
 */
@Data
@Entity
@Table
public class CashStatistics {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 统计日期
     */
    private LocalDate date;
    /**
     * 资金总量
     */
    private BigDecimal cach;
    /**
     * 当日盈亏
     */
    private BigDecimal loss;
    /**
     * 手续费
     */
    private BigDecimal poundage;
}
