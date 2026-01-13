package com.devon.building.repository;

import com.devon.building.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>,BuildingRepositoryCustom {
    BuildingEntity findBuildingsById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
