package com.devon.building.repository;

import com.devon.building.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building,Long> {
    Building findBuildingsById(Long id);
}
