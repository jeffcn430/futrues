package com.hx.futrues.service;

import com.hx.futrues.entity.Orders;

import java.util.List;

public interface IReportService {
    List<Orders> getOrdersByDay();
    List<Orders> getOrdersByWeek();
    List<Orders> getOrdersByMonth();

}
