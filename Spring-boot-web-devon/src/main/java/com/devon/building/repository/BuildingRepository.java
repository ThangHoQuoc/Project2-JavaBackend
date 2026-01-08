package com.devon.building.repository;

import com.devon.building.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>,BuildingRepositoryCustom {
    BuildingEntity findBuildingsById(Long id);
}
