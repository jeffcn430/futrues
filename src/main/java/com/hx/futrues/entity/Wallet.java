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
    private BigDecimal cash;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "platformId", referencedColumnName = "id")
    private Platform platform;

    public void changeCash(BigDecimal cash){
        this.cash = this.cash.add(cash);
    }
}
