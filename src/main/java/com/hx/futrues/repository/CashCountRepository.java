package com.hx.futrues.repository;

import com.hx.futrues.entity.CashCount;
import com.hx.futrues.entity.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CashCountRepository extends JpaRepository<CashCount, Integer> {
    CashCount findByDateEquals(LocalDate date);


}
