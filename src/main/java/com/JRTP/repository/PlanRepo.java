package com.JRTP.repository;

import com.JRTP.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<PlanEntity,Long> {
}
