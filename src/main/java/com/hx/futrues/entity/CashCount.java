package com.hx.futrues.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 资金统计
 */
@Data
@Entity
@Table
public class CashCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * 日期
     */
    private LocalDate date;
    /**
     * 开始资金
     */
    private BigDecimal openCash;
    /**
     * 结束资金
     */
    private BigDecimal closeCash;
    /**
     * 最低金额
     */
    private BigDecimal maxCash;
    /**
     * 最低金额
     */
    private BigDecimal minCash;

    public CashCount() {
    }

    public CashCount(LocalDate date, BigDecimal cash) {
        this.date = date;
        this.openCash = cash;
        this.closeCash = cash;
        this.maxCash = cash;
        this.minCash = cash;
    }

    public void changeCash(BigDecimal cash) {
        this.closeCash = cash;

        if (this.maxCash.compareTo(cash) == -1) {
            this.maxCash = cash;
        }

        if (this.minCash.compareTo(cash) == 1) {
            this.minCash = cash;
        }
    }


    public static void main(String[] dada){
        BigDecimal qian = new BigDecimal(80000/7.75);
        BigDecimal meitian = new BigDecimal(1.05);
        for(int i = 0; i < 200; i++){
            qian = qian.multiply(meitian);
            System.out.println(qian.multiply(new BigDecimal(7.75)).setScale(2, BigDecimal.ROUND_DOWN));
        }


    }
}
