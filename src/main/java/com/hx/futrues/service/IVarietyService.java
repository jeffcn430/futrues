package com.hx.futrues.service;

import com.hx.futrues.entity.Variety;

import java.util.List;

public interface IVarietyService {
    List<Variety> findAllByPlatformId(Integer platformId);
}
