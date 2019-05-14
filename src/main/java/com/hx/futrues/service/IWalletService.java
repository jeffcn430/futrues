package com.hx.futrues.service;

import com.hx.futrues.entity.Platform;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 钱包操作类
 */
public interface IWalletService {
    /**
     * 修改钱包金额
     *
     * @param platform  平台
     * @param type      类型
     * @param cash      现金额度
     * @param projectId 修改金额的项目id
     * @param time      修改时间
     * @return
     */
    @Transactional
    boolean changeCash(Platform platform, Integer type, BigDecimal cash, Integer projectId, LocalDateTime time);
}
