package com.hx.futrues.repository;

import com.hx.futrues.entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VarietyRepository extends JpaRepository<Variety, Integer> {
    List<Variety> findAllByPlatformId(Integer platformId);
}
