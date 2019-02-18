package com.hx.futrues.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
public class Variety implements Serializable {
    /**
     * 品种id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 平台id
     */
    private Integer platformId;
    /**
     * 品种
     */
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "varietyBaseId", referencedColumnName = "id")
    private VarietyBase varietyBase;
    /**
     * 单方向手续费
     * 建仓和平仓分开计算
     */
    private BigDecimal poundage;
}
