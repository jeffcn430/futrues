package com.hx.futrues.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Platform implements Serializable {
    /**
     * 平台id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    /**
     * 平台名称
     */
    public String name;
}
