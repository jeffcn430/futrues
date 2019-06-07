package com.hx.futrues.controller;

import com.hx.futrues.entity.CashCount;
import com.hx.futrues.service.IWalletService;
import com.hx.futrues.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WalletController {
    @Autowired
    private IWalletService walletService;

    @RequestMapping(value = "getCount", method = RequestMethod.GET)
    public ResultData getCount() {
        List<Object[]> list = walletService.getCount();
        return new ResultData(0, "成功", list, list.size());
    }

    @RequestMapping(value = "getCashCount", method = RequestMethod.GET)
    public ResultData getCashCount() {
        List<CashCount> list = this.walletService.getCashCount();

        List<Object> datas = new ArrayList<>();
        List<Object> tmp;

        // 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
        for (CashCount cashCount : list) {
            tmp = new ArrayList<>();
            tmp.add(cashCount.getDate());
            tmp.add(cashCount.getOpenCash());
            tmp.add(cashCount.getCloseCash());
            tmp.add(cashCount.getMinCash());
            tmp.add(cashCount.getMaxCash());


            datas.add(tmp);
        }

        return new ResultData(0, "成功", datas, datas.size());
    }
}
