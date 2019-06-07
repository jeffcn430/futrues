package com.hx.futrues.service.impl;

import com.hx.futrues.common.Constants;
import com.hx.futrues.entity.CashCount;
import com.hx.futrues.entity.CashFlow;
import com.hx.futrues.entity.Platform;
import com.hx.futrues.entity.Wallet;
import com.hx.futrues.exception.FutrueException;
import com.hx.futrues.repository.CashCountRepository;
import com.hx.futrues.repository.CashFlowRepository;
import com.hx.futrues.repository.WalletRepository;
import com.hx.futrues.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletServiceImpl implements IWalletService {
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private CashFlowRepository cashFlowRepository;

    @Autowired
    private CashCountRepository cashCountRepository;

    @Override
    public boolean changeCash(Platform platform, Integer type, BigDecimal cash, Integer projectId, LocalDateTime time) {
        // 获取钱包信息
        Wallet wallet = this.walletRepository.getOne(1);

        // 获取钱包统计
        CashCount cashCount = this.cashCountRepository.findByDateEquals(time.toLocalDate());
        if (cashCount == null) {
            cashCount = new CashCount(time.toLocalDate(), wallet.getCash());
        }

        // 修改金额
        wallet.changeCash(cash);
        //保存钱包
        this.walletRepository.save(wallet);

        cashCount.changeCash(wallet.getCash());
        this.cashCountRepository.save(cashCount);

        // 生成资金流水
        CashFlow cashFlow = new CashFlow(Constants.CASH_TYPE_OFFSET, cash, projectId, time, wallet.getCash());
        // 保存资金流水
        this.cashFlowRepository.save(cashFlow);

        return true;
    }

    @Override
    public List<Object[]> getCount() {
        return this.cashFlowRepository.getCount();
    }

    @Override
    public List<CashCount> getCashCount() {
        return this.cashCountRepository.findAll(Sort.by("id"));
    }

    @Override
    public boolean deleteOrders(Integer orderId) throws FutrueException {
        // 获取钱包信息
        Wallet wallet = this.walletRepository.getOne(1);

        // 获取流水记录
        CashFlow cashFlow = this.cashFlowRepository.findByProjectId(orderId);
        if (cashFlow == null) {
            throw new FutrueException("流水不存在");
        }

        // 生成资金流水
        CashFlow newCashFlow = new CashFlow(Constants.CASH_TYPE_WRITE_OFF, BigDecimal.ZERO.subtract(cashFlow.getCash()), cashFlow.getProjectId(), LocalDateTime.now(), wallet.getCash());
        this.cashFlowRepository.save(newCashFlow);

        // 修改钱包金额
        wallet.changeCash(newCashFlow.getCash());

        // 保存钱包
        this.walletRepository.save(wallet);

        return false;
    }
}
