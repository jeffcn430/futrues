package com.hx.futrues.controller;

import com.hx.futrues.service.IOrdersService;
import com.hx.futrues.utils.ResultData;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    /**
     * 建仓
     *
     * @param type
     * @param bbi
     * @param startPoint
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "orders/openingTransaction", method = RequestMethod.POST)
    public ResultData openingTransaction(Integer type, Integer bbi, BigDecimal startPoint, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime time) {
        this.ordersService.openingTransaction(type, bbi, startPoint, time);
        return new ResultData();
    }

    /**
     * 平仓
     *
     * @param id
     * @param startPoint
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "orders/offsetTransaction", method = RequestMethod.PUT)
    public ResultData offsetTransaction(Integer id, BigDecimal startPoint, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime time) {
        this.ordersService.offsetTransaction(id, startPoint, time);
        return new ResultData();
    }


}
