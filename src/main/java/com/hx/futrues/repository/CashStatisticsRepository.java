package com.hx.futrues.repository;

import com.hx.futrues.entity.Orders;
import com.hx.futrues.entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashStatisticsRepository extends JpaRepository<Orders, Variety> {
}
