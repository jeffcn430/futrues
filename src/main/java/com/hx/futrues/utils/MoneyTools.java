package com.hx.futrues.utils;

import com.hx.futrues.common.Constants;

import java.math.BigDecimal;

public class MoneyTools {
    public static BigDecimal exchangeToUSD(int type, BigDecimal money) {
        switch (type) {
            case Constants.MONEY_TYPE_EUR:
                return money.multiply(new BigDecimal(1.2388));
            case Constants.MONEY_TYPE_HKD:
                return money.multiply(new BigDecimal(0.135));
            default:
                return money;
        }
    }
}
