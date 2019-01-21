package com.hx.futrues.service.impl;

import com.hx.futrues.entity.Orders;
import com.hx.futrues.repository.OrdersRepository;
import com.hx.futrues.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public boolean getOrders() {
        this.ordersRepository.findAll();
        return false;
    }

    @Override
    public boolean openingTransaction(Integer type, Integer bbi, BigDecimal startPoint, LocalDateTime time) {
        Orders orders = new Orders(type, bbi, time, startPoint);
        this.ordersRepository.save(orders);
        return true;
    }

    @Override
    public boolean offsetTransaction(Integer id, BigDecimal endPoint, LocalDateTime endTime) {
        Orders orders = this.ordersRepository.getOne(id);
        orders.offsetTransaction(endPoint, endTime);
        this.ordersRepository.save(orders);
        return true;
    }
}
