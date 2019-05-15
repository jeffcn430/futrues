package com.hx.futrues.controller;

import com.hx.futrues.service.IWalletService;
import com.hx.futrues.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
