package com.hx.futrues.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 现金流水
 */
@Data
@Entity
@Table
public class CashFlow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer type;
    private BigDecimal cash;
    private BigDecimal poorCapital;
    private Integer projectId;
    private LocalDateTime time;

    public CashFlow() {
    }

    public CashFlow(Integer type, BigDecimal cash, Integer projectId, LocalDateTime time) {
        this.type = type;
        this.cash = cash;
        this.projectId = projectId;
        this.time = time;
    }
}
