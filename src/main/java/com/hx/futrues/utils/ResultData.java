package com.hx.futrues.utils;

import lombok.Data;

@Data
public class ResultData {
    private static final String SESS = "成功";

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

    public ResultData() {
        this.code = 1;
        this.msg = SESS;
    }

    public ResultData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
