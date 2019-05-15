package com.hx.futrues.service.impl;

import com.hx.futrues.common.Constants;
import com.hx.futrues.entity.CashFlow;
import com.hx.futrues.entity.Platform;
import com.hx.futrues.entity.Wallet;
import com.hx.futrues.repository.CashFlowRepository;
import com.hx.futrues.repository.WalletRepository;
import com.hx.futrues.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public boolean changeCash(Platform platform, Integer type, BigDecimal cash, Integer projectId, LocalDateTime time) {
        // 获取钱包信息
        Wallet wallet = this.walletRepository.getOne(1);
        // 修改金额
        wallet.changeCash(cash);
        //保存钱包
        this.walletRepository.save(wallet);

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
}
