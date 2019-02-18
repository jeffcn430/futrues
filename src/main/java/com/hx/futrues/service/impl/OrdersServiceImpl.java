package com.hx.futrues.service.impl;

import com.hx.futrues.entity.Orders;
import com.hx.futrues.repository.OrdersRepository;
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
    public boolean openingTransaction(Integer platform, Integer type, Integer bbi, Integer number, BigDecimal startPoint, String time) {
        Orders orders = new Orders(platform, type, bbi, number, time, startPoint, new BigDecimal(36));
        this.ordersRepository.save(orders);
        return true;
    }

    @Override
    public boolean offsetTransaction(Integer id, BigDecimal endPoint, String endTime) {
        Orders orders = this.ordersRepository.getOne(id);
//        if (orders.getStatus() == 1) {
//            return false;
//        }
        orders.offsetTransaction(endPoint, endTime, new BigDecimal(36));
        this.ordersRepository.save(orders);
        return true;
    }
}
