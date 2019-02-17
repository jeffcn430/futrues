package com.hx.futrues.utils;

import lombok.Data;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

@Data
public class ResultData {
    private static final String SUCCESS = "成功";

    /**
     * 状态
     */
    public Integer code;
    /**
     * 状态信息
     */
    public String msg;
    /**
     * 返回数据
     */
    public Object data;
    /**
     * 返回数据数量
     */
    private int count;

    public ResultData() {
        this.code = 0;
        this.msg = SUCCESS;
    }

    public ResultData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultData(Integer code, String msg, Object data, int count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }
}
