package com.hx.futrues.service.impl;

import com.hx.futrues.entity.Platform;
import com.hx.futrues.repository.PlatformRepository;
import com.hx.futrues.service.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformServiceImpl implements IPlatformService {
    @Autowired
    private PlatformRepository platformRepository;

    @Override
    public List<Platform> platforms() {
        return platformRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }
}
