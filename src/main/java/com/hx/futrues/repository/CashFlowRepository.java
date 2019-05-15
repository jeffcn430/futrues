package com.hx.futrues.repository;

import com.hx.futrues.entity.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CashFlowRepository extends JpaRepository<CashFlow, Integer> {
    @Query(value = "select a.date, a.cash, b.poor_capital, a.max, a.min\n" +
            "from (\n" +
            "\tSELECT DATE_FORMAT(time, '%Y-%m-%d') as date, sum(cash) as cash, max(id) maxId,max(poor_capital) max,min(poor_capital) min\n" +
            "\tFROM cash_flow\n" +
            "\twhere type = 3\n" +
            "\tgroup by DATE_FORMAT(time, '%Y-%m-%d')\n" +
            "\torder by DATE_FORMAT(time, '%Y-%m-%d')\n" +
            ") a\n" +
            "left join cash_flow b on a.maxId = b.id"
    , nativeQuery = true)
    List<Object[]> getCount();
}
