package com.hx.futrues.utils;

import java.math.BigDecimal;

public class MoneyTools {
    public static BigDecimal exchange(int type, BigDecimal money) {
        switch (type) {
            case 2:
                return money.multiply(new BigDecimal(1.24));
            case 3:
                return money.multiply(new BigDecimal(0.135));
            default:
                return money;
        }
    }
}
