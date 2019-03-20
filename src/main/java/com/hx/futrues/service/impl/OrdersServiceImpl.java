package com.hx.futrues.service.impl;

import com.hx.futrues.entity.Orders;
import com.hx.futrues.entity.Platform;
import com.hx.futrues.entity.Variety;
import com.hx.futrues.exception.FutrueException;
import com.hx.futrues.repository.OrdersRepository;
import com.hx.futrues.repository.PlatformRepository;
import com.hx.futrues.repository.VarietyRepository;
import com.hx.futrues.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private VarietyRepository varietyRepository;
    @Autowired
    private PlatformRepository platformRepository;

    @Override
    public List<Orders> getOrdersList() {
        return this.ordersRepository.findAll();
    }

    @Override
    public boolean getOrders() {
        this.ordersRepository.findAll();
        return false;
    }

    @Override
    public boolean openingTransaction(Integer platformId, Integer type, Integer bbi, Integer number, BigDecimal startPoint, String time) throws FutrueException {
        Platform platform = this.platformRepository.getOne(platformId);
        if(platform == null){
            throw new FutrueException("指定平台不存在");
        }

        Variety variety = varietyRepository.getOne(type);
        if(variety == null){
            throw new FutrueException("指定品种不存在");
        }

        Orders orders = new Orders(platform, variety, bbi, number, time, startPoint);
        this.ordersRepository.save(orders);
        return true;
    }

    @Override
    public boolean offsetTransaction(Integer id, BigDecimal endPoint, String endTime) {
        Orders orders = this.ordersRepository.getOne(id);
//        if (orders.getStatus() == 1) {
//            return false;
//        }
        orders.offsetTransaction(endPoint, endTime);
        this.ordersRepository.save(orders);
        return true;
    }

    @Override
    public List<Orders> getOrdersCount(Integer type) {
        return null;
    }
}
