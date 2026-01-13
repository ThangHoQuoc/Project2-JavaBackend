package com.devon.building.repository;

import com.devon.building.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {

    List<AssignmentBuildingEntity> findByBuilding_Id(Long buildingId);

    void deleteByBuilding_Id(Long buildingId);

    void deleteAllByBuilding_IdIn(List<Long> buildingIds);

}
