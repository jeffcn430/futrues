package com.hx.futrues.service.impl;

import com.hx.futrues.entity.Variety;
import com.hx.futrues.repository.VarietyRepository;
import com.hx.futrues.service.IVarietyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VarietyServiceImpl implements IVarietyService {
    @Autowired
    private VarietyRepository varietyRepository;

    @Override
    public List<Variety> findAllByPlatformId(Integer platformId) {
        return varietyRepository.findAllByPlatformId(platformId);
    }
}
