package com.hx.futrues.service;

import com.hx.futrues.entity.Platform;

import java.util.List;

/**
 * 平台服务类接口
 */
public interface IPlatformService {
    /**
     * 获取所有平台
     * @return
     */
    List<Platform> platforms();
}
